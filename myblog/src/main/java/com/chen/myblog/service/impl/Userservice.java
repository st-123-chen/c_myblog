package com.chen.myblog.service.impl;

import com.chen.myblog.Dao.IUserdao;
import com.chen.myblog.pojo.User;
import com.chen.myblog.service.IUserservice;
import com.chen.myblog.util.MD5secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userservice implements IUserservice {
    @Autowired
    private IUserdao iUserdao;

    @Override
    public User logincheck(String name, String password) {

//        System.out.println("check");
//        return iUserdao.findByUsernameAndPassword(name, MD5secret.code(password));
        return iUserdao.findByUsername(name);
    }
}
