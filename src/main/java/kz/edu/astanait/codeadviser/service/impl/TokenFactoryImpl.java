package kz.edu.astanait.codeadviser.service.impl;

import kz.edu.astanait.codeadviser.domain.GeneratedToken;
import kz.edu.astanait.codeadviser.entity.TokenEntity;
import kz.edu.astanait.codeadviser.repository.TokenRepository;
import kz.edu.astanait.codeadviser.service.TokenFactory;
import kz.edu.astanait.codeadviser.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TokenFactoryImpl implements TokenFactory {

    private final TokenRepository tokenRepository;
    private final Clock clock;
    private final Duration expireAfter;

    public TokenFactoryImpl(final TokenRepository tokenRepository,
                            final Clock clock,
                            final @Value("${spring.security.token.expire-after}") Duration expireAfter) {
        this.tokenRepository = tokenRepository;
        this.clock = clock;
        this.expireAfter = expireAfter;
    }

    @Transactional
    @Override
    public GeneratedToken create(final String email) {
        var plainText = TokenUtils.createPlainTextToken();
        var hashedToken = TokenUtils.hash(plainText);
        var expiredAt = LocalDateTime.now(clock).plus(expireAfter);
        var token = new TokenEntity(hashedToken, email, expiredAt);
        tokenRepository.deleteAllByOwnerEmail(email);
        tokenRepository.save(token);
        return new GeneratedToken(new String(plainText, StandardCharsets.UTF_8), expiredAt);
    }
}
