package com.perepechko.cryptowatcherapi.service;

import com.perepechko.cryptowatcherapi.entity.CryptoCurrency;

import java.util.List;
import java.util.Optional;

public interface CryptoCurrencyService {
    Optional<CryptoCurrency> findById(long id);
    Optional<CryptoCurrency> findBySymbol(String symbol);
    CryptoCurrency update(CryptoCurrency cryptoCurrency);
    List<CryptoCurrency> findAll();
}
