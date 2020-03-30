package com.cn.lx.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 激励整个周期的时间
 * @author StevenLu
 * @date 2020-03-23 23:31
 */
@Component
@Slf4j
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Long startTime = (Long)currentContext.get("startTime");
        long duration = System.currentTimeMillis() - startTime;
        String requestURI = currentContext.getRequest().getRequestURI();

        log.info("uri = "+requestURI +", duration =" + duration/100 +"ms");
        return null;
    }
}
