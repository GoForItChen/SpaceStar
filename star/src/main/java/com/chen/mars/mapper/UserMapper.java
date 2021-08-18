package com.chen.mars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mars.entity.User;
import com.chen.mars.entity.UserPermission;
import com.chen.mars.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectById(Integer id);

    User selectByUsername(String username);

    List<UserRole> selectListRoleByUserId(Long id);

    List<UserPermission> selectListPermissionByUserId(Long id);

}
