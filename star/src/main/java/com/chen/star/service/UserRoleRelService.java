package com.chen.star.service;

import com.chen.star.base.extension.IService;
import com.chen.star.entity.UserRoleRel;

/**
 * <p>
 * 角色用户关联表 服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
public interface UserRoleRelService extends IService<UserRoleRel> {

    boolean saveRoleRel(Long userId, Integer userRoleId);

}
