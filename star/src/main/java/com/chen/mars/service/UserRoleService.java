package com.chen.mars.service;

import com.chen.mars.base.extension.IService;
import com.chen.mars.entity.UserRole;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */

public interface UserRoleService extends IService<UserRole> {

    Integer saveRole(UserRole userRole);

}
