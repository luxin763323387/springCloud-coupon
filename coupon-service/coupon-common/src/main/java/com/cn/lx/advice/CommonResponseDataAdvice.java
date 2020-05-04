package com.cn.lx.advice;

import com.cn.lx.annotation.IgnoreResponseAdvice;
import com.cn.lx.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一响应
 *
 * @author StevenLu
 * @date 2020-05-04 10:12
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行处理
     *
     * @param methodParameter
     * @param converterType
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        //如果当前方法类带有 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }

        //如果当前方法类带有 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }

        //对响应进行处理，执行 beforeBodyWrite 方法
        return true;
    }

    /**
     * 响应返回前的处理
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        //定义最终的返回对象
        CommonResponse<Object> commonResponse = new CommonResponse<>(
                0, ""
        );
        //如果body 返回内容为null，response 不需要设置data
        if (null == body) {
            return commonResponse;
            // 如果body已经是CommonResponse,不需要再次处理
        } else if (body instanceof CommonResponse) {
            commonResponse = (CommonResponse<Object>) body;
            // 否则,把响应作为对象放入data中
        } else {
            commonResponse.setData(body);
        }

        return commonResponse;
    }
}
