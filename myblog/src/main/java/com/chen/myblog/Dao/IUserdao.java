package com.chen.myblog.Dao;

import com.chen.myblog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLOutput;

public interface IUserdao extends JpaRepository<User,Long> {

//    User findByUsernameAndPassword(String name,String password);
    User findByUsername(String name);
}
