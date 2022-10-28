package com.chen.star.service.impl;

import com.chen.star.base.exception.BaseException;
import com.chen.star.base.extension.impl.ServiceImpl;
import com.chen.star.entity.UserRoleRel;
import com.chen.star.mapper.UserRoleRelMapper;
import com.chen.star.service.UserRoleRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.chen.star.base.exception.Status.REPEAT_ADD;

/**
 * <p>
 * 角色用户关联表 服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements UserRoleRelService {

    @Autowired
    private UserRoleRelMapper userRoleRelMapper;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean saveRoleRel(Long userId, Integer userRoleId) {
        boolean isSuccess = false;
        UserRoleRel params = new UserRoleRel();
        params.setUserId(userId);
        params.setUserRoleId(userRoleId);
        Integer count = userRoleRelMapper.countBy(params);
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
