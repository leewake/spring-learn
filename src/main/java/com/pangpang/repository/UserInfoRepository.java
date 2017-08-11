package com.pangpang.repository;

import com.pangpang.domain.shiro.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by leewake on 2017/8/11 0011.
 */

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    UserInfo findByUsername(String userName);

}
