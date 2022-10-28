package com.chen.star.mapper;

import com.chen.star.entity.UserPermissionRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 权限用户关联表 Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Mapper
public interface UserPermissionRelMapper extends BaseMapper<UserPermissionRel> {

    Integer countBy(@Param("params") UserPermissionRel params);

}
