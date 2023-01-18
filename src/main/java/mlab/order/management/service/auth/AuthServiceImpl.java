package mlab.order.management.service.auth;

import mlab.order.management.exception.AuthException;
import mlab.order.management.model.request.auth.AuthRequest;
import mlab.order.management.model.response.auth.AuthResponse;
import mlab.order.management.util.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService{

    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(CustomUserDetailsService userDetailsService,
                           AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authenticate(AuthRequest request) {
        try{
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            );
            authenticationManager.authenticate(auth);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credential", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = JWTUtils.generateToken(userDetails.getUsername());
        return new AuthResponse(token);
    }

    public UserDetails validateToken(String token){
        String userName = JWTUtils.extractUserName(token);

        if(JWTUtils.isTokenInvalidOrExpired(token))
            throw new AuthException("Authorization failed");

        return userDetailsService.loadUserByUsername(userName);
    }
}
