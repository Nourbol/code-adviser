package kz.edu.astanait.codeadviser.repository;

import kz.edu.astanait.codeadviser.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

    boolean existsByHashAndExpiredAtAfter(byte[] hash, LocalDateTime expiredAtBefore);

    void deleteAllByOwnerEmail(String ownerEmail);
}
