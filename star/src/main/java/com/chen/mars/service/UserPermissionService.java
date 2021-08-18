package com.chen.mars.service;

import com.chen.mars.base.extension.IService;
import com.chen.mars.entity.UserPermission;

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
