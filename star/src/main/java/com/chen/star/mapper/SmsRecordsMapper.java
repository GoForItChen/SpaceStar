package com.chen.star.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.star.entity.SmsRecords;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 短信发送记录表 Mapper 接口
 * </p>
 *
 * @author chenwei
 * @since 2021-08-25
 */
@Mapper
public interface SmsRecordsMapper extends BaseMapper<SmsRecords> {

}
