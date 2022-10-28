package com.chen.star.controller.user;


import com.chen.star.base.GenericController;
import com.chen.star.base.dto.BaseResponse;
import com.chen.star.base.exception.Status;
import com.chen.star.service.UserPermissionRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限用户关联表 前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/user-permission-rel")
public class UserPermissionRelController extends GenericController {

    @Autowired
    private UserPermissionRelService userPermissionRelService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public BaseResponse save(Long userId, Integer userPermissionId) {
        return getBaseResponse(Status.SUCCESS, userPermissionRelService.savePermissionRel(userId, userPermissionId));
    }

}
