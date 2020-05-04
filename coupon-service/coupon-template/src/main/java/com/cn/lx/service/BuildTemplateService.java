package com.cn.lx.service;

import com.cn.lx.entity.CouponTemplate;
import com.cn.lx.exception.CouponException;
import com.cn.lx.vo.TemplateRequest;

/**
 * 构建优惠券模版接口定义
 * @author StevenLu
 * @date 2020-05-04 15:57
 */
public interface BuildTemplateService {

    /**
     * 创建优惠券模版
     * @param request
     * @return
     * @throws CouponException
     */
    CouponTemplate buildTemplate(TemplateRequest request) throws CouponException;
}
