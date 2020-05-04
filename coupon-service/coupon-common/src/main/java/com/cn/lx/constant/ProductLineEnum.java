package com.cn.lx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 产品线
 *
 * @author StevenLu
 * @date 2020-05-04 11:32
 */
@Getter
@AllArgsConstructor
public enum ProductLineEnum {

    DAMAO(1,"大猫"),
    DABAO(2,"大保");

    /** 产品编码 */
    private Integer code;

    /** 产品描述 */
    private String desc;

    public static ProductLineEnum of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " 该code码不存在！"));
    }
}
