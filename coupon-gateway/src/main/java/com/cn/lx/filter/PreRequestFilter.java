package com.cn.lx.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 前置过滤器可以获取时间
 * @author StevenLu
 * @date 2020-03-23 23:20
 */
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {

    /**
     * to classify a filter by type
     * 根据过滤类型分类pre,route,post,error
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 表示过滤器执行的顺序，顺序越小执行权限越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.set("startTime",System.currentTimeMillis());
        return null;
    }
}
