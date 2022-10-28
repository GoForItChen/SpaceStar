package com.chen.star.controller.user;


import com.chen.star.base.GenericController;
import com.chen.star.base.dto.BaseResponse;
import com.chen.star.base.exception.Status;
import com.chen.star.entity.UserPermission;
import com.chen.star.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/user-permission")
public class UserPermissionController extends GenericController {

    @Autowired
    private UserPermissionService userPermissionService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public BaseResponse save(@RequestBody UserPermission userPermission) {
        return getBaseResponse(Status.SUCCESS, userPermissionService.savePermission(userPermission));
    }

}
