package mlab.order.management.service.email;

import mlab.order.management.model.dto.EmailDto;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    CompletableFuture<EmailDto> sendMail(EmailDto emailDto);
}
