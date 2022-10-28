package com.chen.star.service;

import com.chen.star.base.extension.IService;
import com.chen.star.entity.UserRole;

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
