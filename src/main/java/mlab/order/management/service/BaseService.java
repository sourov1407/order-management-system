package mlab.order.management.service;

import lombok.extern.slf4j.Slf4j;
import mlab.order.management.model.dto.EmailDto;
import mlab.order.management.service.email.EmailService;
import mlab.order.management.util.LocaleMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BaseService {

    @Autowired
    private LocaleMessageHandler messageHandler;

    @Autowired
    private EmailService emailService;

    protected String getLocaleMessage(String key){
        return messageHandler.getLocalizedMessage(key);
    }

    protected void sendEmail(EmailDto emailDto) {
        emailService.sendMail(emailDto)
                .thenAcceptAsync(dto -> log.info("Email successfully sent to {}", dto.getEmail()));
        log.info("Sending email to {}........",emailDto.getEmail());
    }

}
