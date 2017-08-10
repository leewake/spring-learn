package com.pangpang.repository;

import com.pangpang.domain.SecKill.SecKillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by leewake on 2017/8/10 0010.
 */

@Repository
public interface SecKillOrderRepository extends JpaRepository<SecKillOrder, String> {

}
