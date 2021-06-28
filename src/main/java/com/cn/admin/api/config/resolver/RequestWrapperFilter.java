package com.cn.admin.api.config.resolver;


import com.cn.admin.api.constant.LogConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 转换封装json文本成为JsonObject对象，方便后面使用
 * Created by yml on 2016/11/1.
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "RequestWrapperFilter")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("=================== 初始化过滤器 ===================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUuid = LogConstant.REQUEST_ID_PREFIX + System.currentTimeMillis();
        MDC.put(LogConstant.REQUEST_ID, requestUuid);
        request.setAttribute(LogConstant.REQUEST_ID, requestUuid);
        try {
            if (servletRequest.getContentType() != null
                    && (servletRequest.getContentType().toLowerCase().contains("json")
            )) {
                RequestBodyWrapper myRequestWrapper = new RequestBodyWrapper((HttpServletRequest) servletRequest);
                filterChain.doFilter(myRequestWrapper, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
