package mlab.order.management.model.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @JsonProperty("user_name")
    private String username;
    @JsonProperty("password")
    private String password;
}
