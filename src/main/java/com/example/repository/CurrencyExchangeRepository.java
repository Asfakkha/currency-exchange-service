package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CurrencyExchangeEntity;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {
	CurrencyExchangeEntity findByFromAndTo(String from, String to);
}
