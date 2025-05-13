package kz.edu.astanait.codeadviser.domain;

import java.time.LocalDateTime;

public record GeneratedToken(String token,
                             LocalDateTime expiredAt) {
}
