package com.perepechko.cryptowatcherapi.controller;

import com.perepechko.cryptowatcherapi.entity.CryptoCurrency;
import com.perepechko.cryptowatcherapi.entity.User;
import com.perepechko.cryptowatcherapi.service.CryptoCurrencyService;
import com.perepechko.cryptowatcherapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crypto-api")
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final UserService userService;

    @Autowired
    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService, UserService userService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.userService = userService;
    }

    @GetMapping("/currencies")
    public List<CryptoCurrency> findAll() {
        return cryptoCurrencyService.findAll();
    }

    @GetMapping("/currencies/{symbol}")
    public CryptoCurrency findCurrencyBySymbol(@PathVariable String symbol) {
        return cryptoCurrencyService.findBySymbol(symbol).get();
    }

    @PostMapping("/notify")
    public ResponseEntity notifyUser(@RequestParam("username") String username,
                                     @RequestParam("symbol") String symbol) {
        Double price_usd = cryptoCurrencyService.findBySymbol(symbol).get().getPrice_usd();
        User user = User.builder()
                .username(username)
                .symbol(symbol)
                .price_usd(price_usd)
                .build();
        userService.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}