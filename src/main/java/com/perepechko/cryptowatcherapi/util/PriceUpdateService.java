package com.perepechko.cryptowatcherapi.util;

import com.perepechko.cryptowatcherapi.entity.CryptoCurrency;
import com.perepechko.cryptowatcherapi.entity.User;
import com.perepechko.cryptowatcherapi.service.CryptoCurrencyService;
import com.perepechko.cryptowatcherapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PriceUpdateService {
    private final String URL = "https://api.coinlore.net/api/ticker?id=";
    private static final double MAX_CHANGE_VALUE = 1.0;
    private final long updateTime = 60000L;

    private final CryptoCurrencyService cryptoCurrencyService;
    private final UserService userService;

    @Autowired
    public PriceUpdateService(CryptoCurrencyService cryptoCurrencyService, UserService userService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.userService = userService;
    }

    @Scheduled(fixedRate = updateTime)
    public void updateCurrencyDB() {
        List<CryptoCurrency> currencies = cryptoCurrencyService.findAll();
        for (CryptoCurrency currency:currencies) {
            updatePrice(currency);
            priceChangeNotify(currency);
        }
    }


    private void updatePrice(CryptoCurrency currency) {
        String url = URL+currency.getId();
        ResponseEntity<List> response = new RestTemplate().getForEntity(url, List.class);
        Map<String, Object> data = (Map<String, Object>) response.getBody().get(0);
        Double price_usd = Double.parseDouble((String) data.get("price_usd")) ;
        currency.setPrice_usd(price_usd);
        currency.setTime(LocalDateTime.now());
        cryptoCurrencyService.update(currency);
    }

    private void priceChangeNotify(CryptoCurrency currency) {
        List<User> users = userService.findBySymbol(currency.getSymbol());
        for (User user : users) {
            double initialPrice = user.getPrice_usd();
            double actualPrice = currency.getPrice_usd();
            double changeInPercent = calculateChange(initialPrice, actualPrice);
            if (changeInPercent > MAX_CHANGE_VALUE) {
                log.warn(String.format("Username: %s, Currency: %s, price change: %.3f",
                        user.getUsername(), currency.getSymbol(), changeInPercent));
            }

        }
    }

    private double calculateChange(double initialPrice, double actualPrice) {
        return Math.abs((initialPrice - actualPrice) / initialPrice) * 100;
    }


}
