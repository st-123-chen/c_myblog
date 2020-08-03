package com.chen.myblog.service;

import com.chen.myblog.pojo.User;

public interface IUserservice {
    User logincheck(String name,String password);
}
