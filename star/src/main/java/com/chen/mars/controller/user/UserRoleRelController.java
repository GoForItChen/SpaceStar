package com.chen.mars.controller.user;


import com.chen.mars.base.GenericController;
import com.chen.mars.base.dto.BaseResponse;
import com.chen.mars.base.exception.Status;
import com.chen.mars.service.UserRoleRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色用户关联表 前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/user-role-rel")
public class UserRoleRelController extends GenericController {

    @Autowired
    private UserRoleRelService userRoleRelService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public BaseResponse save(Long userId, Integer userRoleId) {
        return getBaseResponse(Status.SUCCESS, userRoleRelService.saveRoleRel(userId, userRoleId));
    }

}
