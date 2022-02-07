package com.perepechko.cryptowatcherapi.repository;

import com.perepechko.cryptowatcherapi.entity.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    Optional<CryptoCurrency> findBySymbol(String symbol);
}
