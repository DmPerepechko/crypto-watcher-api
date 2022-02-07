package com.perepechko.cryptowatcherapi.service.impl;

import com.perepechko.cryptowatcherapi.entity.CryptoCurrency;
import com.perepechko.cryptowatcherapi.repository.CryptoCurrencyRepository;
import com.perepechko.cryptowatcherapi.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    public CryptoCurrencyServiceImpl(CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }


    @Override
    public Optional<CryptoCurrency> findById(long id) {
        return cryptoCurrencyRepository.findById(id);
    }

    @Override
    public Optional<CryptoCurrency> findBySymbol(String symbol) {
        return cryptoCurrencyRepository.findBySymbol(symbol);
    }


    @Override
    public CryptoCurrency update(CryptoCurrency cryptoCurrency) {
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }

    @Override
    public List<CryptoCurrency> findAll() {
        return cryptoCurrencyRepository.findAll();
    }
}
