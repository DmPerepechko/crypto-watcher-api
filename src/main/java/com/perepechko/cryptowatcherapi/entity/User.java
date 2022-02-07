package com.perepechko.cryptowatcherapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price_usd")
    private Double price_usd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.price_usd, price_usd) == 0 && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(symbol, user.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, symbol, price_usd);
    }

    @Override
    public String toString() {
        return "id: " + id + ", username: " + username +
                ", symbol: '" + symbol + '\'' +
                ", price_usd: " + price_usd;
    }
}
