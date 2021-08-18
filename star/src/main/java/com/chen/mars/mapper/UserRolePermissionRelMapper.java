package com.chen.mars.mapper;

import com.chen.mars.entity.UserRolePermissionRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 权限角色关联表 Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Mapper
public interface UserRolePermissionRelMapper extends BaseMapper<UserRolePermissionRel> {

    Integer countBy(@Param("params") UserRolePermissionRel params);

}
