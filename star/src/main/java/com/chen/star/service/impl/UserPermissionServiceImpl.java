package com.chen.star.service.impl;

import com.chen.star.base.extension.impl.ServiceImpl;
import com.chen.star.entity.UserPermission;
import com.chen.star.mapper.UserPermissionMapper;
import com.chen.star.service.UserPermissionService;
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
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer savePermission(UserPermission userPermission) {
        Integer isSuccess = -1;
        Date now = new Date();
        if (userPermission.getId() != null) {
            userPermission.setUpdateTime(now);
            isSuccess = userPermissionMapper.updateById(userPermission);
        } else {
            userPermission.setCreateTime(now);
            isSuccess = userPermissionMapper.insert(userPermission);
        }
        return isSuccess;
    }
}
