package com.chen.myblog.Dao;

import com.chen.myblog.pojo.blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface Iblogdao extends JpaRepository<blog,Long>, JpaSpecificationExecutor<blog> {
    @Query("select b from blog b where b.published=true and b in(select b from blog b where b.title like ?1 or b.content like ?1)")
    Page<blog> findByQuery(String query, Pageable pageable);


    /*查询推荐博客展示在首页最新推荐模块*/
    @Query("select b from blog b where b.recommend=true and b.published=true")
    List<blog> findTop(Pageable pageable);
//
//

}
