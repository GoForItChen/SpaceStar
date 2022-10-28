package com.chen.star.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 短信发送记录表
 * </p>
 *
 * @author chenwei
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_records")
@ApiModel(value="SmsRecords对象", description="短信发送记录表")
public class SmsRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "短信码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "短信类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "短信状态")
    @TableField("status")
    private String status;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private Date createTime;


}
