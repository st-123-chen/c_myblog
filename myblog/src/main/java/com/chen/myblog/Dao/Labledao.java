package com.chen.myblog.Dao;

import com.chen.myblog.pojo.Type;
import com.chen.myblog.pojo.lable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Labledao extends JpaRepository<lable,Long> {
    lable findByName(String name);

    @Query("select t from lable t")
    List<lable> findTop(Pageable pageable);
}
