package com.chen.star.controller.account;


import com.chen.star.base.GenericController;
import com.chen.star.base.dto.BaseResponse;
import com.chen.star.base.exception.Status;
import com.chen.star.dto.RegisterParams;
import com.chen.star.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前台客户表 前端控制器
 * </p>
 *
 * @author chenwei
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/account")
public class AccountController extends GenericController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ApiOperation(value="用户注册", response = BaseResponse.class)
    public BaseResponse register(@RequestBody RegisterParams params) {
        Boolean isSuccess = accountService.register(params);
        return getBaseResponse(Status.SUCCESS, isSuccess);
    }

}
