package mlab.order.management.controller;

import mlab.order.management.exception.BadRequestException;
import mlab.order.management.util.LocaleMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class BaseController {

    @Autowired
    private LocaleMessageHandler messageHandler;

    private String getErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(messageHandler::getLocalizedMessage)
                .collect(Collectors.joining(", "));
    }

    protected void chekIfHasError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(getErrors(bindingResult)
            );
        }
    }
}
