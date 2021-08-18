package com.chen.mars.service.impl;

import com.chen.mars.base.extension.impl.ServiceImpl;
import com.chen.mars.entity.UserRole;
import com.chen.mars.mapper.UserRoleMapper;
import com.chen.mars.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer saveRole(UserRole userRole) {
        Integer isSuccess = -1;
        Date now = new Date();
        if (userRole.getId() != null) {
            userRole.setUpdateTime(now);
            isSuccess = userRoleMapper.updateById(userRole);
        } else {
            userRole.setCreateTime(now);
            isSuccess = userRoleMapper.insert(userRole);
        }
        return isSuccess;
    }
}
