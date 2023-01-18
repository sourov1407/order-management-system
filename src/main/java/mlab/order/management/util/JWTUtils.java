package mlab.order.management.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mlab.order.management.properties.JWTProperties;
import mlab.order.management.security.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtils {

    private static final JWTProperties prop;

    static {
        prop = ApplicationContextHolder.getContext().getBean(JWTProperties.class);
    }

    public static String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public static String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public static String trimToken(String bearerToken){
        if(StringUtils.startsWith(bearerToken, prop.getTokenPrefix())){
            return StringUtils.replace(bearerToken, prop.getTokenPrefix(), "").trim();
        }
        return bearerToken;
    }

    public static boolean isTokenValid(String token, String username){
        boolean isValidUsername = StringUtils.equals(extractUserName(token), username);
        return ( isValidUsername && !isTokenExpired(token));
    }

    public static boolean isTokenInvalidOrExpired(String token, String username){
        return !isTokenValid(token, username);
    }

    public static boolean isTokenInvalidOrExpired(String token){
        return !isTokenValid(StringUtils.trimToEmpty(token), extractUserName(token));
    }

    public static boolean isTokenFormatValid(String bearerToken){
        return StringUtils.isNotBlank(bearerToken) &&
                bearerToken.matches(prop.getTokenValidationRegex());
    }

    private static String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + getExpiryMilli()))
                .signWith(SignatureAlgorithm.HS256, prop.getSigningKey())
                .compact();
    }

    private static int getExpiryMilli(){
        return getTimeUnitInMillis();
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(extractAllClaims(token));
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(prop.getSigningKey())
                .parseClaimsJws(trimToken(token))
                .getBody();
    }

    private static boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private static int getTimeUnitInMillis() {
        return 1000 * 60 * prop.getTokenValidity();
    }
}
