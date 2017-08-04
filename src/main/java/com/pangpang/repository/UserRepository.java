package com.pangpang.repository;

import com.pangpang.Domain.User;
/**
 * Created by leewake on 2017/8/4 0004.
 */
public interface UserRepository {
    User getByUserName(String userName);

}
