package com.chen.myblog.service;

import com.chen.myblog.pojo.blog;
import com.chen.myblog.vo.blogquery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Iblogservice {

    blog getblog(Long id);

    Page<blog> listblog(Pageable pageable, blogquery blog);

    blog saveblog(blog blog);

    blog updateblog(Long id,blog blog);

    Page<blog> listblog(Pageable pageable);
    List<blog> listblogrecommend(Integer size);

    void  deleteblog(Long id);

    Page<blog> listblog(String query,Pageable pageable);
}
