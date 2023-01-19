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
    @NotBlank(message = "validation.property.user.username.NotBlank.message")
    @JsonProperty("user_name")
    private String username;
    @NotBlank(message = "validation.property.user.password.NotBlank.message")
    private String password;
    @NotBlank(message = "validation.property.user.fullName.NotBlank.message")
    @JsonProperty("full_name")
    private String fullName;
    @Email(message = "validation.property.user.email.Email.message")
    private String email;
    @NotNull(message = "validation.property.user.roles.NotNull.message")
    private List<String> roles;
}
