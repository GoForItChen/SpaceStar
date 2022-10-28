package com.chen.star.service;

import com.chen.star.base.extension.IService;
import com.chen.star.entity.UserRolePermissionRel;

/**
 * <p>
 * 权限角色关联表 服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
public interface UserRolePermissionRelService extends IService<UserRolePermissionRel> {

    boolean saveRolePermissionRel(Integer userRoleId, Integer userPermissionId);

}
