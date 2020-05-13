package com.cn.lx.constant;

/**
 * 常量定义
 * @author StevenLu
 * @date 2020-05-13 22:31
 */
public class Constant {

    /** kafka 消息的Topic*/
    public static final String TOPIC = "user_coupon_operation";

    /**
     * Redis key 前缀定义
     */
    public static class RedisPrefix{

        /** 优化券吗 key前缀*/
        public static final String COUPON_TEMPLATE = "COUPON_TEMPLATE_CODE_";

        /** 用户当前所有可用的优惠券 key前缀*/
        public static final String USER_COUPON_USABLE = "USER_COUPON_USABLE_";

        /** 用户当前所有已使用的优惠券 key前缀*/
        public static final String USER_COUPON_USED = "USER_COUPON_USED_";

        /** 用户当前已经过期的优惠券 key前缀*/
        public static final String USER_COUPON_EXPIRED = "USER_COUPON_EXPIRED_";
    }
}
