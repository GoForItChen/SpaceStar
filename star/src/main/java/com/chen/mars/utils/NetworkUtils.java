/*
 * @(#)NetworkUtils.java 2014-2-25 上午11:38:01
 * Copyright 2014 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.chen.mars.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * <p>File：NetworkUtils.java</p>
 * <p>Title: 网络处理工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-25 上午11:38:01</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public abstract class NetworkUtils
{
    /**
     * 取得当前host地址<解决font face跨域问题，nginx下尝试失败>
     *
     * @param request HttpServletRequest
     * @return String 当前host地址
     */
    public static String getHost(HttpServletRequest request)
    {
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return path;
    }
    
    /**
     * 将IP地址转换为整数类型
     *
     * @param addr 字符串类型的IP地址
     * @return 整数
     */
    public static int ipToInt(final String addr)
    {
        int ip = 0;
        if (StringUtils.isNotBlank(addr))
        {
            final String[] addressBytes = addr.split("\\.");
            ip = 0;
            for (int i = 0; i < 4; i++)
            {
                ip <<= 8;
                ip |= Integer.parseInt(addressBytes[i]);
            }
        }
        return ip;
    }
    
    /**
     * 将整数类型的IP地址转换为字符串类型的IP地址
     *
     * @param i 整数
     * @return IP地址
     */
    public static String intToIp(int i)
    {
        if (i == 0)
        { return ""; }
        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
    }
    
    /**
     * 根据网卡取本机配置的IP
     * 如果是双网卡的，则取出外网IP
     *
     * @return
     */
    public static String getNetIp()
    {
        // 本地IP，如果没有配置外网IP则返回它
        String localip = null;
        // 外网IP
        String netip = null;
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            // 是否找到外网IP
            boolean finded = false;
            while (netInterfaces.hasMoreElements() && !finded)
            {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements())
                {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                    {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    }
                    else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                    {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException e)
        {
            log.error(e.getMessage());
        }
        if (netip != null && !"".equals(netip))
        {
            return netip;
        }
        else
        {
            return localip;
        }
    }
    
    /**
     * 根据网卡取本机配置的内网IP
     * 如果是双网卡的，则取出内网IP
     *
     * @return String 内网IP地址
     */
    public static String getLocalIp()
    {
        // 本地IP，如果没有配置外网IP则返回它
        String localip = null;
        try
        {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            // 是否找到外网IP
            boolean finded = false;
            while (netInterfaces.hasMoreElements() && !finded)
            {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements())
                {
                    ip = address.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                    {
                        localip = ip.getHostAddress();
                        finded = true;
                        break;
                    }
                }
            }
        }
        catch (SocketException e)
        {
            log.error(e.getMessage());
        }
        return localip;
    }

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，
     * X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
    
    /**
     * 取得客户端IP地址并转化为整数
     *
     * @param request HttpServletRequest
     * @return int 整数格式的客户端IP地址
     */
    public static int getIpAddrs(HttpServletRequest request)
    {
        int ipInt = 0;
        String ip = getIpAddr(request);
        if (StringUtils.isNotBlank(ip))
        {
            if (IPAddressUtil.isIPv4LiteralAddress(ip))
            {
                ipInt = NetworkUtils.ipToInt(ip);
            }
            else
            {
                String[] string = ip.split(",");
                int iLen = string.length;
                if (iLen > 0)
                {
                    ip = StringUtils.trimToEmpty(string[iLen - 1]);
                }
                if (IPAddressUtil.isIPv4LiteralAddress(ip))
                {
                    ipInt = NetworkUtils.ipToInt(ip);
                }
            }
        }
        return ipInt;
    }
}
