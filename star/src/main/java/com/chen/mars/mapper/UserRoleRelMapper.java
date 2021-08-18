package com.chen.mars.mapper;

import com.chen.mars.entity.UserRoleRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色用户关联表 Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Mapper
public interface UserRoleRelMapper extends BaseMapper<UserRoleRel> {

    List<UserRoleRel> selectListBy(@Param("params") UserRoleRel params);

    Integer countBy(@Param("params") UserRoleRel params);

}
