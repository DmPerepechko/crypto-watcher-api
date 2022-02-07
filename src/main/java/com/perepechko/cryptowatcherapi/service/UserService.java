package com.perepechko.cryptowatcherapi.service;

import com.perepechko.cryptowatcherapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);
    List<User> findBySymbol(String symbol);
    User save(User user);
    List<User> findAll();
}
