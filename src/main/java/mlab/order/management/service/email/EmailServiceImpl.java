package mlab.order.management.service.email;

import lombok.extern.slf4j.Slf4j;
import mlab.order.management.model.dto.EmailDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{

    @Override
    @Async
    public CompletableFuture<EmailDto> sendMail(EmailDto emailDto) {
        return CompletableFuture.completedFuture(emailDto);
    }
}
