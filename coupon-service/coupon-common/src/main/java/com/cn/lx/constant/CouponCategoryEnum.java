package com.cn.lx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 券分类
 *
 * @author StevenLu
 * @date 2020-05-04 11:21
 */
@Getter
@AllArgsConstructor
public enum CouponCategoryEnum {

    MANJIAN("001", "满减券"),
    ZHEKOU("002", "折扣券"),
    LIJIAN("003", "立减券");

    /**
     * 优惠券分类编码
     */
    private String code;

    /**
     * 优惠券描述
     */
    private String desc;

    public static CouponCategoryEnum of(String code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " 该code码不存在！"));
    }
}
