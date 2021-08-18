package com.chen.mars.base.service;

import com.chen.mars.base.dto.UserPrincipal;
import com.chen.mars.entity.User;
import com.chen.mars.entity.UserPermission;
import com.chen.mars.entity.UserRole;
import com.chen.mars.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthLoginService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        List<UserRole> roles = userMapper.selectListRoleByUserId(user.getId());
        List<UserPermission> permissions = userMapper.selectListPermissionByUserId(user.getId());
        return UserPrincipal.create(user, roles, permissions);
    }
}
