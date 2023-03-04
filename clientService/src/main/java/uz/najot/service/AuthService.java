package uz.najot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.najot.entity.AppUser;
import uz.najot.entity.RefreshToken;
import uz.najot.model.ResponseModel;
import uz.najot.repository.AppUserRepository;
import uz.najot.repository.RefreshTokenRepository;
import uz.najot.security.JwtTokenUtil;
import uz.najot.security.RefreshTokenUtil;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenUtil refreshTokenUtil;

    @Transactional
    public ResponseModel loginUser(String username, String password){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String jwt = jwtTokenUtil.jwtGenerator((UserDetails) authenticate.getPrincipal());
            RefreshToken refreshToken = refreshTokenUtil.generateRefreshToken((AppUser) authenticate.getPrincipal());
            return getResponseModel(jwt, refreshToken);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
    public ResponseModel refreshToken(String refreshToken){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        if (optionalRefreshToken.isPresent() && optionalRefreshToken.get().getExpireDate().getTime()>=new Date().getTime()){
            AppUser user = optionalRefreshToken.get().getUser();
            refreshTokenRepository.delete(optionalRefreshToken.get());
            String jwt = jwtTokenUtil.jwtGenerator(user);
            RefreshToken generateRefreshToken = refreshTokenUtil.generateRefreshToken(user);
            return getResponseModel(jwt, generateRefreshToken);
        }else {
            return new ResponseModel(401,"Refresh token is not valid",null);
        }

    }

    private ResponseModel getResponseModel(String jwt, RefreshToken generateRefreshToken) {
        refreshTokenRepository.save(generateRefreshToken);
        Map<String,Object> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("expiry_time",1000*60);
        map.put("issued_at",new Date());
        map.put("refreshToken",generateRefreshToken.getRefreshToken());
        return ResponseModel.getSuccess(map);
    }


}
