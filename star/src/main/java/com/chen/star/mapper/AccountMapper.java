package com.chen.star.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.star.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 前台客户表 Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2021-08-25
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
