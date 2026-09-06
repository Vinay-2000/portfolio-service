package com.papertrading.portfolio.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "PORTFOLIO",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_PORTFOLIO_USER",
                        columnNames = "USER_ID"
                )
        }
)
public class Portfolio {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "portfolio_seq"
    )
    @SequenceGenerator(
            name = "portfolio_seq",
            sequenceName = "SEQ_PORTFOLIO",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "USER_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PORTFOLIO_USER")
    )
    private User user;

    @Column(
            name = "CASH_BALANCE",
            nullable = false,
            precision = 19,
            scale = 8
    )
    private BigDecimal cashBalance = BigDecimal.ZERO;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}