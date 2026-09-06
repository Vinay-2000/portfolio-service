package com.papertrading.portfolio.dao;

import com.papertrading.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioDao extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByUserId(Long userId);
}