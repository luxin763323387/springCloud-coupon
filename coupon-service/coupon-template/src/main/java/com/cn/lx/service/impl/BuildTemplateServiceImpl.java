package com.cn.lx.service.impl;

import com.alibaba.fastjson.JSON;
import com.cn.lx.dao.CouponTemplateDao;
import com.cn.lx.entity.CouponTemplate;
import com.cn.lx.exception.CouponException;
import com.cn.lx.service.AsyncService;
import com.cn.lx.service.BuildTemplateService;
import com.cn.lx.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author StevenLu
 * @date 2020-05-14 00:00
 */
@Slf4j
@Service
public class BuildTemplateServiceImpl implements BuildTemplateService {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private CouponTemplateDao couponTemplateDao;

    @Override
    public CouponTemplate buildTemplate(TemplateRequest request) throws CouponException {

        if(!request.validate()){
            log.error("构建券模版异常:{}", JSON.toJSON(request));
            throw new CouponException("构建券模版异常");
        }

        if(null !=couponTemplateDao.findByName(request.getName())){
            log.error("构建券模已存在:{}", JSON.toJSON(request.getName()));
            throw new CouponException("构建券模版已存在");
        }

        CouponTemplate template = couponTemplateDao.save(buildTemplate(request));

        //根据优惠券模版异步生成优惠券码
        asyncService.asyncConstructCouponByTemplate(template);
        return template;
    }

    /**
     * 转换
     * @param request
     * @return
     */
    private CouponTemplate requestToTemplate(TemplateRequest request){
        return new CouponTemplate(
                request.getName(),
                request.getLogo(),
                request.getDesc(),
                request.getCategory(),
                request.getProductLine(),
                request.getCount(),
                request.getUserId(),
                request.getTarget(),
                request.getRule()
        );
    }
}
