package com.chen.star.service.impl;

import com.chen.star.base.aop.SlaveDataSource;
import com.chen.star.base.extension.impl.ServiceImpl;
import com.chen.star.entity.User;
import com.chen.star.mapper.UserMapper;
import com.chen.star.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean register(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return this.save(user);
    }

    @Override
    @SlaveDataSource
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }
}
