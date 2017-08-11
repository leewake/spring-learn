package com.pangpang.service;

import com.pangpang.domain.shiro.UserInfo;

/**
 * Created by leewake on 2017/8/11 0011.
 */
public interface UserInfoService {

    public UserInfo findByUserName(String userName);

}
