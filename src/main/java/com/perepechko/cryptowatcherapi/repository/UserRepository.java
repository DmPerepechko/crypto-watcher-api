package com.perepechko.cryptowatcherapi.repository;

import com.perepechko.cryptowatcherapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySymbol(String symbol);
}
