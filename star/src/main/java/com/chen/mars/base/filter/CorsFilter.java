package com.chen.mars.base.filter;

import com.chen.mars.base.dto.GlobalProperies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CorsFilter
 * <p>File: CorsFilter.java </p>
 * <p>Title: CorsFilter </p>
 * <p>Description: CorsFilter </p>
 * <p>Copyright: Copyright (c) 2018/10/31</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter
{
    @Autowired(required = false)
    private GlobalProperies properies;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", properies.getAllowOrigin());
        response.setHeader("Access-Control-Allow-Methods", properies.getAllowMethods());
        response.setHeader("Access-Control-Max-Age", properies.getMaxAge());
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod()))
        {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else
        {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig)
    {
    }

    @Override
    public void destroy()
    {
    }
}
