package com.chen.star.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.star.entity.User;
import com.chen.star.entity.UserPermission;
import com.chen.star.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectById(Integer id);

    User selectByUsername(String username);

    List<UserRole> selectListRoleByUserId(Long id);

    List<UserPermission> selectListPermissionByUserId(Long id);

}
