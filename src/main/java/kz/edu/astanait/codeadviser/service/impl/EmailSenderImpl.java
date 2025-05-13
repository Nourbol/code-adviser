package kz.edu.astanait.codeadviser.service.impl;

import kz.edu.astanait.codeadviser.exception.FailedEmailSendingException;
import kz.edu.astanait.codeadviser.service.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender emailSender;
    private final String senderEmail;

    public EmailSenderImpl(final JavaMailSender emailSender,
                           final @Value("${spring.mail.username}") String senderEmail) {
        this.emailSender = emailSender;
        this.senderEmail = senderEmail;
    }

    @Override
    public void send(final String to, final String subject, final String text) {
        try {
            var message = emailSender.createMimeMessage();
            message.setSubject(subject);
            var helper  = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (final Exception exception) {
            throw new FailedEmailSendingException("There is some troubles with sending emails", exception);
        }
    }
}
