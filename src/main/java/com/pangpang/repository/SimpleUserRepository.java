package com.pangpang.repository;

import com.pangpang.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by leewake on 2017/8/4 0004.
 */

@Repository
public class SimpleUserRepository  implements UserRepository{

    @Override
    @Cacheable(value = "usercache", keyGenerator = "keyGenerator")
    public User getByUserName(String userName) {
        System.out.println("无缓存的时候调用这里");
        return new User(userName, "linda@qq.com", "13628465834");
    }
}
