package mlab.order.management.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank(message = "UserName can not be empty")
    @JsonProperty("user_name")
    private String username;
    @NotBlank(message = "Password can not be empty")
    private String password;
    @NotBlank(message = "Name can not be empty")
    @JsonProperty("full_name")
    private String fullName;
    @Email(message = "Email address is invalid")
    private String email;
    @NotNull(message = "Roles can not be empty")
    private List<String> roles;
}
