package com.cn.lx.service;

import com.cn.lx.entity.CouponTemplate;
import com.cn.lx.exception.CouponException;
import com.cn.lx.vo.CouponTemplateSDK;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 券模版的基础服务
 * @author StevenLu
 * @date 2020-05-05 09:33
 */
public interface TemplateBaseService {

    /**
     * 根据id获取券模版信息
     * @param id
     * @return
     * @throws CouponException
     */
    CouponTemplate getTemplateInfo(Integer id) throws CouponException;

    /**
     * 查找所以可用的券模版
     * @return
     */
    List<CouponTemplateSDK> getCouponTemplateSDK();

    /**
     * 根据ids获取CouponTemplateSDK的映射
     * @param ids
     * @return
     */
    Map<Integer,CouponTemplateSDK> getIds2TemplateSDK(Collection<Integer> ids);
}
