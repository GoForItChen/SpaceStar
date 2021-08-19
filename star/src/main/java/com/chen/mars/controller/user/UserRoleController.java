package com.chen.mars.controller.user;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.mars.base.GenericController;
import com.chen.mars.base.dto.BaseResponse;
import com.chen.mars.base.exception.Status;
import com.chen.mars.entity.UserRole;
import com.chen.mars.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/user-role")
public class UserRoleController extends GenericController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(params = "id")
    public BaseResponse getById(Long id) {
        return getBaseResponse(Status.SUCCESS, userRoleService.getById(id));
    }

    @GetMapping
    public BaseResponse list(Page<UserRole> page, UserRole userRole) {
        return getBaseResponse(Status.SUCCESS, userRoleService.page(page, userRole));
    }

    @PostMapping
    public BaseResponse save(@RequestBody UserRole userRole) {
        System.out.println("cherry pick test");
        return getBaseResponse(Status.SUCCESS, userRoleService.saveRole(userRole));
    }

}
