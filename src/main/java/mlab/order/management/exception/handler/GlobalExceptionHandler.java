package mlab.order.management.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mlab.order.management.exception.AuthException;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.exception.RecordNotFoundException;
import mlab.order.management.exception.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestHandler(BadRequestException e) {
        log.error(e.getMessage());
        return getErrorResponse(ApiError.builder().
                status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> recordNotFoundExceptionHandler(RecordNotFoundException e) {
        log.error(e.getMessage());
        return getErrorResponse(ApiError.builder().
                status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authExceptionHandler(AuthException e) {
        log.error(e.getMessage());
        return getErrorResponse(ApiError.builder().
                status(HttpStatus.UNAUTHORIZED)
                .message(e.getMessage())
                .build());
    }

    private ResponseEntity<ApiError> getErrorResponse(ApiError apiError) {
        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }


}
