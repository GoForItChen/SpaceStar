package com.chen.mars.service;


import com.chen.mars.base.extension.IService;
import com.chen.mars.entity.User;

public interface UserService extends IService<User> {

    boolean register(User user);

    User getById(Integer id);

}
