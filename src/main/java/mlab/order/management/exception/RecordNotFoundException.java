package mlab.order.management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
