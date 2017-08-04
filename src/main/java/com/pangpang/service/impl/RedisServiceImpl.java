package com.pangpang.service.impl;

import com.pangpang.domain.User;
import com.pangpang.repository.UserRepository;
import com.pangpang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leewake on 2017/8/4 0004.
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private UserRepository userRepository;

    public User getUser() {
        return userRepository.getByUserName("linda");
    }
}
