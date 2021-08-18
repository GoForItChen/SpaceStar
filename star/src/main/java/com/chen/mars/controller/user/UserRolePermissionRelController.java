package com.chen.mars.controller.user;


import com.chen.mars.base.GenericController;
import com.chen.mars.base.dto.BaseResponse;
import com.chen.mars.base.exception.Status;
import com.chen.mars.service.UserRolePermissionRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限角色关联表 前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/user-role-permission-rel")
public class UserRolePermissionRelController extends GenericController {

    @Autowired
    private UserRolePermissionRelService userRolePermissionRelService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public BaseResponse save(Integer userRoleId, Integer userPermissionId) {
        return getBaseResponse(Status.SUCCESS, userRolePermissionRelService.saveRolePermissionRel(userRoleId, userPermissionId));
    }

}
