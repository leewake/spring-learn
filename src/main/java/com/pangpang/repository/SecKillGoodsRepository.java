package com.pangpang.repository;

import com.pangpang.domain.SecKill.SecKillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by leewake on 2017/8/10 0010.
 */

@Repository
public interface SecKillGoodsRepository extends JpaRepository<SecKillGoods, String> {

    @Query("update SecKillGoods g set g.remainNum = g.remainNum - ?2 where g.id = ?1 and g.remainNum > 0")
    @Modifying(clearAutomatically = true)
    @Transactional
    int reduceStock(String id, Integer remainNum);

}
