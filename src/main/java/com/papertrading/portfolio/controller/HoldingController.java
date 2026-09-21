package com.papertrading.portfolio.controller;

import com.papertrading.portfolio.api.HoldingsApi;
import com.papertrading.portfolio.api.model.CreateHoldingRequest;
import com.papertrading.portfolio.api.model.HoldingResponse;
import com.papertrading.portfolio.entity.Holding;
import com.papertrading.portfolio.service.HoldingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HoldingController implements HoldingsApi {

    private final HoldingService holdingService;

    public HoldingController(HoldingService holdingService) {
        this.holdingService = holdingService;
    }

    @Override
    public ResponseEntity<HoldingResponse> createHolding(
            Long portfolioId,
            CreateHoldingRequest request) {

        Holding holding = holdingService.createHolding(
                portfolioId,
                request.getSymbol(),
                request.getQuantity(),
                request.getAveragePrice()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(holding));
    }

    @Override
    public ResponseEntity<List<HoldingResponse>> getPortfolioHoldings(
            Long portfolioId) {

        List<HoldingResponse> responses = holdingService
                .getHoldingsByPortfolio(portfolioId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<HoldingResponse> getHolding(
            Long portfolioId,
            String symbol) {

        Holding holding = holdingService.getHolding(
                portfolioId,
                symbol
        );

        return ResponseEntity.ok(toResponse(holding));
    }

    private HoldingResponse toResponse(Holding holding) {

        HoldingResponse response = new HoldingResponse();

        response.setId(holding.getId());
        response.setPortfolioId(holding.getPortfolio().getId());
        response.setSymbol(holding.getSymbol());
        response.setQuantity(holding.getQuantity());
        response.setAveragePrice(holding.getAveragePrice());
        response.setCreatedAt(holding.getCreatedAt());
        response.setUpdatedAt(holding.getUpdatedAt());

        return response;
    }
}