package com.perepechko.cryptowatcherapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cryptocurrency")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price_usd")
    private Double price_usd;

    @Column(name = "time")
    private LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return Double.compare(that.price_usd, price_usd) == 0 && Objects.equals(id, that.id) && Objects.equals(symbol, that.symbol) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, price_usd, time);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", symbol: '" + symbol + '\'' +
                ", price_usd: " + price_usd + ", latest update: "+time;
    }
}
