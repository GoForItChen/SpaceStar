package com.chen.mars.base.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 基础配置
 *
 * @author Playguy
 */
@Data
@Configuration
@ConfigurationProperties("com.chen")
public class GlobalProperies
{
    private String  projectName   = "star";
    
    private String  domainName    = "star.com";
    
    private String  emailProvider = "generic";
    
    private String  defalutPass   = "123456";
    
    private String  cookiePath    = "/";
    
    private Boolean cookieSecure  = false;
    
    private String  tradeUrl      = "";
    
    private String  timeFormat    = "yyyy-MM-dd HH:mm:ss";
    
    private String  passWord      = "123456";
    
    private boolean openLog       = false;
    
    private boolean switchRemind  = false;
    
    private boolean esEnable      = false;
    
    private String  running       = "dev";
    
    /**** 跨域设置 ****/
    private String  allowOrigin   = "*";
    
    private String  allowMethods  = "POST,GET,OPTIONS,DELETE";
    
    private String  maxAge        = "3600";
    
}
