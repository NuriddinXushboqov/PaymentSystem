package uz.najot.security;

import org.springframework.stereotype.Component;
import uz.najot.entity.AppUser;
import uz.najot.entity.RefreshToken;


import java.util.Date;
import java.util.UUID;

@Component
public class RefreshTokenUtil {

    public RefreshToken generateRefreshToken(AppUser appUser){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(new Date().getTime()+(1000L *60*60*24*30*3)));
        refreshToken.setUser(appUser);
        return refreshToken;
    }
}
