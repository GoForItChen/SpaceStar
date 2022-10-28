package com.chen.star.service;

import com.chen.star.entity.Account;
import com.chen.star.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public Account findByAccountname(String accountname) {
        Account result = accountMapper.findByAccountname(accountname);
        result.setPhone("123");
        return result;
    }

}
