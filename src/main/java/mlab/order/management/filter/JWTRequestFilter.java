package mlab.order.management.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mlab.order.management.properties.JWTProperties;
import mlab.order.management.service.auth.AuthService;
import mlab.order.management.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private AuthService authService;

    @Autowired
    private final JWTProperties props;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authToken = request.getHeader(props.getAuthHeaderName());
        if(JWTUtils.isTokenFormatValid(authToken)) {
            UserDetails userDetails = authService.validateToken(authToken);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        log.info("{} Triggered for URI {}", this.getClass().getName(), request.getRequestURI());

        filterChain.doFilter(request, response);
    }
}
