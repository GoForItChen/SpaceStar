package com.chen.star.service;


import com.chen.star.base.extension.IService;
import com.chen.star.dto.RegisterParams;
import com.chen.star.entity.Account;

/**
 * <p>
 * 前台客户表 服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-08-25
 */
public interface AccountService extends IService<Account> {

    Boolean register(RegisterParams params);

}
