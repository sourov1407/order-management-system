package mlab.order.management.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    private int tokenValidity;
    private String signingKey;
    private String tokenPrefix;
    private String authHeaderName;
    private String tokenValidationRegex;

}