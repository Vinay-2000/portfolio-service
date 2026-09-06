package com.papertrading.portfolio.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "REFRESH_TOKENS",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_REFRESH_TOKEN_HASH",
                        columnNames = "TOKEN_HASH"
                )
        }
)
public class RefreshToken {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "refresh_token_seq"
    )
    @SequenceGenerator(
            name = "refresh_token_seq",
            sequenceName = "SEQ_REFRESH_TOKENS",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "USER_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_REFRESH_TOKEN_USER")
    )
    private User user;

    @Column(name = "TOKEN_HASH", nullable = false, length = 255)
    private String tokenHash;

    @Column(name = "EXPIRES_AT", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "REVOKED", nullable = false)
    private Boolean revoked = false;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
