package com.papertrading.portfolio.service;

import com.papertrading.portfolio.dao.PortfolioDao;
import com.papertrading.portfolio.dao.UserDao;
import com.papertrading.portfolio.entity.Portfolio;
import com.papertrading.portfolio.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioDao portfolioDao;
    private final UserDao userDao;

    public PortfolioServiceImpl(PortfolioDao portfolioDao, UserDao userDao) {
        this.portfolioDao = portfolioDao;
        this.userDao = userDao;
    }

    @Override
    public Portfolio createPortfolio(
            Long userId,
            BigDecimal initialCash) {
        User user = userDao.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found: " + userId
                        )
                );

        Portfolio portfolio = new Portfolio();
        portfolio.setUser(user);
        portfolio.setCashBalance(initialCash);

        return portfolioDao.save(portfolio);
    }

    @Override
    public Portfolio getPortfolio(Long portfolioId) {

        return portfolioDao.findById(portfolioId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Portfolio not found: " + portfolioId
                        )
                );
    }

    @Override
    public Portfolio getPortfolioByUserId(Long userId) {

        return portfolioDao.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Portfolio not found for user: " + userId
                        )
                );
    }
}