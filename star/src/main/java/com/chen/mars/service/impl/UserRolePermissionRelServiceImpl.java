package com.chen.mars.service.impl;

import com.chen.mars.base.exception.BaseException;
import com.chen.mars.base.extension.impl.ServiceImpl;
import com.chen.mars.entity.UserRolePermissionRel;
import com.chen.mars.mapper.UserRolePermissionRelMapper;
import com.chen.mars.service.UserRolePermissionRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.chen.mars.base.exception.Status.REPEAT_ADD;

/**
 * <p>
 * 权限角色关联表 服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Service
public class UserRolePermissionRelServiceImpl extends ServiceImpl<UserRolePermissionRelMapper, UserRolePermissionRel> implements UserRolePermissionRelService {

    @Autowired
    private UserRolePermissionRelMapper userRolePermissionRelMapper;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean saveRolePermissionRel(Integer userRoleId, Integer userPermissionId) {
        boolean isSuccess = false;
        UserRolePermissionRel params = new UserRolePermissionRel();
        params.setUserRoleId(userRoleId);
        params.setUserPermissionId(userPermissionId);
        Integer count = userRolePermissionRelMapper.countBy(params);
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
