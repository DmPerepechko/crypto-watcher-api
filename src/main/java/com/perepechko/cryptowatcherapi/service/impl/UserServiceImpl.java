package com.perepechko.cryptowatcherapi.service.impl;

import com.perepechko.cryptowatcherapi.entity.User;
import com.perepechko.cryptowatcherapi.repository.UserRepository;
import com.perepechko.cryptowatcherapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findBySymbol(String symbol) {
        return userRepository.findBySymbol(symbol);
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
