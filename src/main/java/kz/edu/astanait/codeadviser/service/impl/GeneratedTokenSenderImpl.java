package kz.edu.astanait.codeadviser.service.impl;

import kz.edu.astanait.codeadviser.service.EmailSender;
import kz.edu.astanait.codeadviser.service.GeneratedTokenSender;
import kz.edu.astanait.codeadviser.service.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GeneratedTokenSenderImpl implements GeneratedTokenSender {

    private static final String GENERATED_API_KEY_EMAIL_TEMPLATE = """
        <!doctype html>
        <html>
            <head>
                <meta name="viewport" content="width=device-width" />
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            </head>
            <body>
                <p>Hi,</p>
                <p>Thanks for using Code Adviser. We're excited to have you on board!</p>
                <p>Here is your API key: %s</p>
                <p>It will expire at %s</p>
                <p>Thanks,</p>
                <p>The Code Adviser Team</p>
            </body>
        </html>
        """;

    private static final String GENERATED_API_KEY_EMAIL_SUBJECT = "API key for your email!";

    private final TokenFactory tokenFactory;
    private final EmailSender emailSender;

    @Override
    @Transactional
    public void sendGeneratedToken(final String email) {
        var generatedToken = tokenFactory.create(email);
        emailSender.send(
                email,
                GENERATED_API_KEY_EMAIL_SUBJECT,
                GENERATED_API_KEY_EMAIL_TEMPLATE.formatted(generatedToken.token(), generatedToken.expiredAt())
        );
    }
}
