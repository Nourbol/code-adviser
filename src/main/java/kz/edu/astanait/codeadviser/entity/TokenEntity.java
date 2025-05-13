package kz.edu.astanait.codeadviser.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenEntity {
    @Id
    private UUID id = UUID.randomUUID();
    private byte[] hash;
    private String ownerEmail;
    private LocalDateTime expiredAt;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public TokenEntity(byte[] hash, String ownerEmail, LocalDateTime expiredAt) {
        this.hash = hash;
        this.ownerEmail = ownerEmail;
        this.expiredAt = expiredAt;
    }
}
