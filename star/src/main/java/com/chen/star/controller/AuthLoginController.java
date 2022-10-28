package com.chen.star.controller;

import com.chen.star.base.GenericController;
import com.chen.star.base.dto.BaseResponse;
import com.chen.star.base.exception.Status;
import com.chen.star.base.jwt.JwtUtil;
import com.chen.star.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthLoginController extends GenericController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createJwt(authentication, user.getIsRememberMe());
        return getBaseResponse(Status.SUCCESS, jwt);
    }


    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse logout() {
        return getBaseResponse(Status.SUCCESS, null);
    }

}
