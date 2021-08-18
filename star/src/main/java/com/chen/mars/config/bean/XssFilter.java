package com.chen.mars.config.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Xss攻击拦截器
 *
 */
@Slf4j
public class XssFilter implements Filter
{
    // 是否过滤富文本内容
    private boolean      flag     = false;

    private List<String> excludes = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig)
    {
        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
        if (StringUtils.isNotBlank(isIncludeRichText))
        {
            flag = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null)
        {
            String[] url = temp.split(",");
            excludes.addAll(Arrays.asList(url));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        if (handleExcludeURL(req))
        {
            log.info("自定义过滤器filter触发,不拦截url:{}", ((HttpServletRequest) request).getRequestURI());
            chain.doFilter(request, response);
            return;
        }
        RequestWrapper xssRequest = new RequestWrapper((HttpServletRequest) request, flag);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy()
    {
        // do nothing
    }

    private boolean handleExcludeURL(HttpServletRequest request)
    {
        if (excludes == null || excludes.isEmpty())
        { return false; }
        String url = request.getServletPath();
        log.info("handleExcludeURL,拦截url:{}", url);
        return excludes.stream().map(pattern -> Pattern.compile("^" + pattern)).map(p -> p.matcher(url)).anyMatch(Matcher::find);
    }
}
