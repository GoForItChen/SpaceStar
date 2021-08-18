/*
 * @(#)ZttxConst.java 2015-4-14 下午2:02:23
 * Copyright 2015 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.chen.mars.base.consts;

/**
 * <p>File：GlobalConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-14 下午2:02:23</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class GlobalConst
{
    private GlobalConst()
    {// 防止实例化
    }
    
    /**
     * 分割符
     */
    public static final char    SEPARATOR             = ':';
    
    public static final String  DEFAULT_COUNTRY       = "86";
    
    /**
     * 默认语言
     */
    public static final String  DEFAULT_LANG          = "zh_CN";
    
    /**
     * 默认值
     */
    public static final String  DEFAULT_VALUE         = "object";
    
    /**
     * 官方账户NickName (哔哔News)
     */
    public static final String  DEFAULT_NICKNAME      = "哔哔News";
    
    /**
     * 默认UNID
     */
    public static final Long    DEFAULT_UNID          = 10000L;
    
    /**
     * 请求类型
     */
    public static final String  POST                  = "post";
    
    public static final String  GET                   = "get";
    
    /**
     * 当前页面
     */
    public static final Integer DEFAULT_CURRENT_PAGE  = 1;
    
    /**
     * 分页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE     = 10;
    
    /**
     * 分页起始大小
     */
    public static final Integer DEFAULT_START_INDEX   = 0;
    
    /**
     * 批处理大小
     */
    public static final Integer DEFAULT_BATCH_SIZE    = 100;
    
    /**
     * 默认限制次数
     */
    public static final Integer DEFAULT_REQUEST_LIMIT = 10;
    
    /**
     * 模块
     */
    public static final String  SYSTEM                = "/system";
    
    public static final String  COMMON                = "/common";
    
    public static final String  ACCOUNT               = "/account";
    
    public static final String  BUSINESS              = "/business";
    
    public static final String  ADVERT                = "/advert";
    
    public static final String  COMMENT               = "/comment";
    
    public static final String  SEARCH                = "/search";
    
    public static final String  LIVE                  = "/live";
    
    public static final String  REPLY                 = "/reply";
    
    public static final String  NOTICE                = "/notice";
    
    public static final String  PRAISE                = "/praise";
    
    public static final String  FAVORITE              = "/favorite";
    
    public static final String  FLASH                 = "/flash";
    
    public static final String  OAUTH                 = "/oauth";
    
    public static final String  NEWS                  = "/news";
    
    public static final String  VOTE                  = "/vote";
    
    public static final String  VIDEO                 = "/video";
}
