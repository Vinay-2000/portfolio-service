package com.papertrading.portfolio.controller;

import com.papertrading.portfolio.api.PortfoliosApi;
import com.papertrading.portfolio.api.model.CreatePortfolioRequest;
import com.papertrading.portfolio.api.model.PortfolioResponse;
import com.papertrading.portfolio.entity.Portfolio;
import com.papertrading.portfolio.service.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController implements PortfoliosApi {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public ResponseEntity<PortfolioResponse> createPortfolio(
            CreatePortfolioRequest request) {

        Portfolio portfolio = portfolioService.createPortfolio(
                request.getUserId(),
                request.getInitialCash()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(portfolio));
    }

    @Override
    public ResponseEntity<PortfolioResponse> getPortfolio(
            Long portfolioId) {

        return ResponseEntity.ok(
                toResponse(
                        portfolioService.getPortfolio(portfolioId)
                )
        );
    }

    @Override
    public ResponseEntity<PortfolioResponse> getUserPortfolio(
            Long userId) {

        return ResponseEntity.ok(
                toResponse(
                        portfolioService.getPortfolioByUserId(userId)
                )
        );
    }

    private PortfolioResponse toResponse(Portfolio portfolio) {

        PortfolioResponse response = new PortfolioResponse();

        response.setId(portfolio.getId());
        response.setUserId(portfolio.getUser().getId());
        response.setCashBalance(portfolio.getCashBalance());
        response.setCreatedAt(portfolio.getCreatedAt());
        response.setUpdatedAt(portfolio.getUpdatedAt());

        return response;
    }
}