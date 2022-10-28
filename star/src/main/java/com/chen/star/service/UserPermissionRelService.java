package com.chen.star.service;

import com.chen.star.base.extension.IService;
import com.chen.star.entity.UserPermissionRel;

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
