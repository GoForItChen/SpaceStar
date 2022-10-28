package com.chen.star.service.impl;

import com.chen.star.base.extension.impl.ServiceImpl;
import com.chen.star.dto.RegisterParams;
import com.chen.star.entity.Account;
import com.chen.star.mapper.AccountMapper;
import com.chen.star.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 前台客户表 服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2021-08-25
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public Boolean register(RegisterParams params) {
        Account account = new Account();
        account.setAccountname(params.getLoginname());
        account.setEmail(params.getEmail());
        account.setNickname(params.getNicckname());
        account.setPhone(params.getPhone());
        account.setPassword(new BCryptPasswordEncoder().encode(params.getPassword()));
        return this.save(account);
    }
}
