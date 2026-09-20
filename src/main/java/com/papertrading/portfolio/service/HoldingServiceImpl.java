package com.papertrading.portfolio.service;

import com.papertrading.portfolio.dao.HoldingDao;
import com.papertrading.portfolio.dao.PortfolioDao;
import com.papertrading.portfolio.entity.Holding;
import com.papertrading.portfolio.entity.Portfolio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HoldingServiceImpl implements HoldingService {

    private final HoldingDao holdingDao;
    private final PortfolioDao portfolioDao;

    public HoldingServiceImpl(
            HoldingDao holdingDao,
            PortfolioDao portfolioDao) {

        this.holdingDao = holdingDao;
        this.portfolioDao = portfolioDao;
    }

    @Override
    public Holding createHolding(
            Long portfolioId,
            String symbol,
            BigDecimal quantity,
            BigDecimal averagePrice) {

        Portfolio portfolio = portfolioDao.findById(portfolioId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Portfolio not found: " + portfolioId
                        )
                );

        Holding holding = new Holding();

        holding.setPortfolio(portfolio);
        holding.setSymbol(symbol);
        holding.setQuantity(quantity);
        holding.setAveragePrice(averagePrice);

        return holdingDao.save(holding);
    }

    @Override
    public Holding getHolding(Long holdingId) {

        return holdingDao.findById(holdingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Holding not found: " + holdingId
                        )
                );
    }

    @Override
    public List<Holding> getHoldingsByPortfolio(Long portfolioId) {

        return holdingDao.findByPortfolioId(portfolioId);
    }

    @Override
    public Holding getHolding(Long portfolioId, String symbol) {
        return holdingDao.findByPortfolioIdAndSymbol(portfolioId, symbol)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Holding not found for portfolio: "
                                        + portfolioId + ", symbol: " + symbol
                        ));
    }
}