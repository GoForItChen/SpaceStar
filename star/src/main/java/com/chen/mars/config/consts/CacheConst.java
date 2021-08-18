package com.chen.mars.config.consts;

/**
 * <p>File：CacheConst.java </p>
 * <p>Title: 缓存前缀声明 </p>
 * <p>Description: CacheConst </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class CacheConst
{
    private CacheConst()
    {
    }
    
    /**
     * 1分钟
     */
    public static final Integer ONE_MINUTE_CACHE_TIME      = 60;
    
    /**
     * The default interval: 3000 ms = 3 seconds.
     */
    public static final long    DEFAULT_INTERVAL           = 3000;
    
    /**
     * APP参数默认缓存时间(5分钟)
     */
    public static final Integer DEFAULT_CACHE_TIME         = 300;
    
    /**
     * 15分钟
     */
    public static final Integer FIFTEEN_MINUTE_CACHE_TIME  = 900;
    
    /**
     * 30分钟
     */
    public static final Integer THIRTY_MINUTE_CACHE_TIME   = 1800;
    
    /**
     * 60分钟
     */
    public static final Integer ONE_HOUR_CACHE_TIME        = 3600;
    
    /**
     * 24小时
     */
    public static final Integer TWENTYFOUR_HOUR_CACHE_TIME = 86400;
    
    public static final String  LOGIN_PERFIX               = "login:perf";
    
    /**
     * GOOGLE CODE
     */
    public static final String  GOOGLE_CODE_PERFIX         = "google:code";
    
    /**
     * 找回密码
     */
    public static final String  FIND_PASSWD_PERFIX         = "cache:findpass";
    
    /**
     * 找回密码
     */
    public static final String  CHANGE_PHONE_PERFIX        = "cache:change";
    
    /**
     * 注册密码
     */
    public static final String  REGISTER_PERFIX            = "cache:register";
    
    /**
     * 消息发送
     */
    public static final String  CACHE_SEND_SMS_PERFIX      = "message:phone";
    
    /**
     * 消息邮件
     */
    public static final String  CACHE_SEND_EMAIL_PERFIX    = "message:email";
    
    /**
     * 消息过期
     */
    public static final String  CACHE_EXPIRE_SMS_PERFIX    = "message:expire";
    
    /**
     * BOSS会话对象
     */
    public static final String  BOSS_SHIRO_CACHE_PREFIX    = "session:boss";
    
    /**
     * WEB会话对象
     */
    public static final String  WEB_SHIRO_CACHE_PREFIX     = "session:web";
    
    /**
     * 微信ticker
     */
    public static final String  WEIXIN_TICKET_PERFIX       = "wx|ticket|expire";
    
    /**
     * COOKIE
     */
    public static final String  BOSS_COOKIE_ID             = "bid";
    
    public static final String  WEB_COOKIE_ID              = "sid";
}
