package com.papertrading.portfolio.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Table(
        name = "HOLDINGS",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_HOLDINGS_PORTFOLIO_SYMBOL",
                        columnNames = {
                                "PORTFOLIO_ID",
                                "SYMBOL"
                        }
                )
        }
)
@Entity
public class Holding {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "holding_seq"
    )
    @SequenceGenerator(
            name = "holding_seq",
            sequenceName = "SEQ_HOLDINGS",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "PORTFOLIO_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_HOLDINGS_PORTFOLIO")
    )
    private Portfolio portfolio;

    @Column(name = "SYMBOL", nullable = false, length = 20)
    private String symbol;

    @Column(
            name = "QUANTITY",
            nullable = false,
            precision = 19,
            scale = 8
    )
    private BigDecimal quantity = BigDecimal.ZERO;

    @Column(
            name = "AVERAGE_PRICE",
            nullable = false,
            precision = 19,
            scale = 8
    )
    private BigDecimal averagePrice = BigDecimal.ZERO;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}