package uz.najot.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.najot.entity.AppClient;
import uz.najot.repository.AppClientRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final AppClientRepository appClientRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            Optional<AppClient> byToken = appClientRepository.findByToken(token);
            if (byToken.isPresent()){
                AppClient appClient = byToken.get();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(appClient.getAppName(),appClient.getToken(),null));
            }
        }
        filterChain.doFilter(request,response);
    }

}
