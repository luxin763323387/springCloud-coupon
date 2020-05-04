package com.cn.lx.service;

import com.cn.lx.entity.CouponTemplate;

/**
 * 异步服务接口定义
 * @author StevenLu
 * @date 2020-05-04 21:09
 */
public interface AsyncService {

    /**
     * 根据券模版异步的创建优惠券吗
     * @param couponTemplate {@link CouponTemplate} 优惠券模版
     */
    void asyncConstructCouponByTemplate(CouponTemplate couponTemplate);
}
