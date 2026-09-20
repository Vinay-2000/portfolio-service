package com.papertrading.portfolio.service;

import com.papertrading.portfolio.entity.Holding;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HoldingService {

    Holding createHolding(
            Long portfolioId,
            String symbol,
            BigDecimal quantity,
            BigDecimal averagePrice
    );

    Holding getHolding(Long holdingId);

    List<Holding> getHoldingsByPortfolio(Long portfolioId);

    Holding getHolding(Long portfolioId, String symbol);

}