package com.chen.star.service;


import com.chen.star.base.extension.IService;
import com.chen.star.entity.User;

public interface UserService extends IService<User> {

    boolean register(User user);

    User getById(Integer id);

}
