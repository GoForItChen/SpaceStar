package com.chen.star.service.impl;

import com.chen.star.base.exception.BaseException;
import com.chen.star.base.extension.impl.ServiceImpl;
import com.chen.star.entity.UserPermissionRel;
import com.chen.star.mapper.UserPermissionRelMapper;
import com.chen.star.service.UserPermissionRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.chen.star.base.exception.Status.REPEAT_ADD;

/**
 * <p>
 * 权限用户关联表 服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Service
public class UserPermissionRelServiceImpl extends ServiceImpl<UserPermissionRelMapper, UserPermissionRel> implements UserPermissionRelService {

    @Autowired
    private UserPermissionRelMapper userPermissionRelMapper;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean savePermissionRel(Long userId, Integer userPermissionId) {
        boolean isSuccess = false;
        UserPermissionRel params = new UserPermissionRel();
        params.setUserId(userId);
        params.setUserPermissionId(userPermissionId);
        Integer count = userPermissionRelMapper.countBy(params);
        if (count > 0) {
            throw new BaseException(REPEAT_ADD);
        } else {
            Date now = new Date();
            params.setCreateTime(now);
            isSuccess = this.save(params);
        }

        return isSuccess;
    }
}
