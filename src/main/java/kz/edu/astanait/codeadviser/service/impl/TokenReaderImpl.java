package kz.edu.astanait.codeadviser.service.impl;

import kz.edu.astanait.codeadviser.repository.TokenRepository;
import kz.edu.astanait.codeadviser.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenReaderImpl implements TokenReader {

    private final TokenRepository tokenRepository;
    private final Clock clock;

    @Override
    public boolean exists(final String token) {
        var hashedToken = TokenUtils.hash(token);
        return tokenRepository.existsByHashAndExpiredAtAfter(hashedToken, LocalDateTime.now(clock));
    }
}
