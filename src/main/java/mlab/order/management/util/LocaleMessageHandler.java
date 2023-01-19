package mlab.order.management.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class LocaleMessageHandler {
    private final HttpServletRequest request;
    private final MessageSource messageSource;

    public String getLocalizedMessage(String key, Object ... objects) {
        return messageSource.getMessage(key, objects, request.getLocale());
    }
}
