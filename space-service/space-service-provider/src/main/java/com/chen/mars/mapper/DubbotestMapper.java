package com.chen.mars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mars.entity.Dubbotest;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2021-07-28
 */
@Mapper
public interface DubbotestMapper extends BaseMapper<Dubbotest> {

    Dubbotest selectBy(Integer id);

}
