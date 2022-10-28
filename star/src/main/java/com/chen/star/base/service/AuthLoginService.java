package com.chen.star.base.service;

import com.chen.star.base.dto.UserPrincipal;
import com.chen.star.entity.User;
import com.chen.star.entity.UserPermission;
import com.chen.star.entity.UserRole;
import com.chen.star.mapper.UserMapper;
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
