package com.chen.mars.service;

import com.chen.mars.base.extension.IService;
import com.chen.mars.entity.UserRoleRel;

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
