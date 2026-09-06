package com.papertrading.portfolio.service;

import com.papertrading.portfolio.entity.Portfolio;

import java.math.BigDecimal;

public interface PortfolioService {

    Portfolio createPortfolio(Long userId, BigDecimal initialCash);

    Portfolio getPortfolio(Long portfolioId);

    Portfolio getPortfolioByUserId(Long userId);
}