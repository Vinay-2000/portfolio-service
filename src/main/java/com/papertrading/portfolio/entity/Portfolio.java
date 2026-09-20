package com.papertrading.portfolio.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
    private OffsetDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}