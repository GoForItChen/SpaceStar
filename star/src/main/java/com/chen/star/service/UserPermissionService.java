package com.chen.star.service;

import com.chen.star.base.extension.IService;
import com.chen.star.entity.UserPermission;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
public interface UserPermissionService extends IService<UserPermission> {

    Integer savePermission(UserPermission userPermission);

}
