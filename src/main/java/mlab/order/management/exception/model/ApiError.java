package mlab.order.management.exception.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
}
