package com.cn.lx.service.impl;

import com.cn.lx.constant.Constant;
import com.cn.lx.dao.CouponTemplateDao;
import com.cn.lx.entity.CouponTemplate;
import com.cn.lx.service.AsyncService;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 异步服务接口实现
 * @author StevenLu
 * @date 2020-05-13 22:39
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    private final CouponTemplateDao couponTemplateDao;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public AsyncServiceImpl(CouponTemplateDao couponTemplateDao, StringRedisTemplate redisTemplate) {
        this.couponTemplateDao = couponTemplateDao;
        this.redisTemplate = redisTemplate;
    }

    /**根据券模版异步的创建优惠券吗*/
    @Override
    @Async("getAsyncExecutor")
    public void asyncConstructCouponByTemplate(CouponTemplate couponTemplate) {

        Stopwatch watch = Stopwatch.createStarted();
        Set<String> couponCodes = buildCouponCode(couponTemplate);

        // COUPON_TEMPLATE_CODE_1
        String redisKey = String.format("/%s%s",
                Constant.RedisPrefix.COUPON_TEMPLATE,couponTemplate.getId().toString());
        Long number = redisTemplate.opsForList().rightPushAll(redisKey, couponCodes);
        log.info("存放在redis的数量:{}",number);

        couponTemplate.setAvailable(true);
        couponTemplateDao.save(couponTemplate);

        watch.stop();
        log.info("创建优化券耗时:{}ms",watch.elapsed(TimeUnit.MILLISECONDS));
        //todo 发送短信或者邮件
    }

    /**
     * 构造优惠券码
     * 优惠券码(每一张优惠券，18位)
     * 前四位: 产品线 + 类型
     * 中间六位: 日期随机(200501)
     * 后八位: 0 ~ 9 随机数构成
     * @param template
     * @return Set<String> 与template.count 相同个数的优惠券码</>
     */
    private Set<String> buildCouponCode(CouponTemplate template){

        Stopwatch watch = Stopwatch.createStarted();

        Set<String> result = new HashSet<>(template.getCount());

        //前四位
        String prefix4 = template.getProductLine().getCode().toString()
                + template.getCategory().getCode();

        String date = new SimpleDateFormat("yyMMdd").format(template.getCreateTime());
        //后14位
        for (int i = 0; i != template.getCount(); ++i){
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        //同while防止数量不够
        while (result.size() < template.getCount()){
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        watch.stop();
        log.info("构建优惠券花了多久时间",watch.elapsed(TimeUnit.MILLISECONDS));

        return result;
    }

    /**
     * 构造优惠券的后14位
     * */
    private String buildCouponCodeSuffix14(String date){

        char[] bases = new char[]{'1','2','3','4','5','6','7','8','9'};

        //中间六位
        List<Character> chars = date.chars()
                .mapToObj(e ->(char)e)
                .collect(Collectors.toList());

        Collections.shuffle(chars);
        String mid6 = chars.stream()
                .map(Objects::toString)
                .collect(Collectors.joining());

        //后八位
        String suffix8 = RandomStringUtils.random(1,bases)
                + RandomStringUtils.randomNumeric(7);

        return mid6 + suffix8;
    }

}



