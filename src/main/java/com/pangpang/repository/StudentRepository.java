package com.pangpang.repository;

import com.pangpang.domain.optimisticlock.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by leewake on 2017/8/9 0009.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

    @Query("update Student set name=?1 where id=?2")
    @Modifying
    @Transactional
    int updateNameById(String name, String id);
}
