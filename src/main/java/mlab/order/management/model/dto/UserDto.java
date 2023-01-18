package mlab.order.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class UserDto {
    @JsonProperty("full_name")
    private String name;
    @JsonProperty("user_name")
    private String userName;
    private String email;
    private List<String> roles;

}
