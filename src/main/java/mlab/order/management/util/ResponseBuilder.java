package mlab.order.management.util;

import mlab.order.management.model.common.CommonResponse;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    public static <T> CommonResponse<T> getResponse(T entity, HttpStatus status){
        return CommonResponse.<T>builder()
                .responseStatus(status)
                .responseBody(entity)
                .build();
    }

    public static <T> CommonResponse<T> getSuccessResponse(T entity){
        return getResponse(entity, HttpStatus.OK);
    }
}
