package com.chen.star.dto;

import com.chen.star.base.dto.MessageParams;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@ApiModel(description = "注册信息")
public class RegisterParams extends MessageParams implements Serializable {


    @NotNull(message = "会员登录名不可为空")
    @ApiModelProperty(value = "会员登录名")
    private String loginname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nicckname;
}
