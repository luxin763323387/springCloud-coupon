package com.cn.lx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 有效期类型
 * @author StevenLu
 * @date 2020-05-04 13:25
 */
@Getter
@AllArgsConstructor
public enum PeriodTypeEnum {

    /**固定类型*/
    REGULAR(1,"固定的(固定日期)"),

    /**变动类型*/
    SHIFT(2,"变动的(以领取之日开始计算)");

    /**券有效期code*/
    private Integer code;
    /**券描述*/
    private String desc;

    public static PeriodTypeEnum of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " 该code码不存在！"));
    }
}
