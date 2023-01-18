package mlab.order.management.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Builder
public class CommonResponse<T> implements Serializable{
    @JsonProperty("response_code")
    private HttpStatus responseStatus;
    @JsonProperty("response_body")
    private T responseBody;
}
