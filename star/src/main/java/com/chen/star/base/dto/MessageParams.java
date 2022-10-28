package com.chen.star.base.dto;

import com.chen.star.base.consts.GlobalConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 帐户信息参数接收对象
 * <p>File：AccountParams.java</p>
 * <p>Title: AccountParams</p>
 * <p>Description: AccountParams</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "帐户参数接收对象")
public class MessageParams implements Serializable
{
    
    @ApiModelProperty(value = "国家代码")
    @NotNull(message = "国家代码不可为空")
    private String            country          = GlobalConst.DEFAULT_COUNTRY;
    
    @ApiModelProperty(value = "手机号码")
    @NotNull(message = "手机号码不可为空")
    private String            phone;
    
    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不可为空")
    private String            kaptcha;
    
    @ApiModelProperty(value = "短信码")
    @NotNull(message = "短信码不可为空")
    private String            code;
    
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不可为空")
    private String            password;
}
