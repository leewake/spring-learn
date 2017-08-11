package com.pangpang.service.impl;

import com.pangpang.domain.shiro.UserInfo;
import com.pangpang.repository.UserInfoRepository;
import com.pangpang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leewake on 2017/8/11 0011.
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUserName(String userName) {
        return userInfoRepository.findByUsername(userName);
    }

}
