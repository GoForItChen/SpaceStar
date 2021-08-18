package com.chen.mars.service;

import com.chen.mars.base.extension.IService;
import com.chen.mars.entity.UserPermissionRel;

/**
 * <p>
 * 权限用户关联表 服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
public interface UserPermissionRelService extends IService<UserPermissionRel> {

    boolean savePermissionRel(Long userId, Integer userPermissionId);

}
