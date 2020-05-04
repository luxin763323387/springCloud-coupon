package com.cn.lx.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 分发目标
 * @author StevenLu
 * @date 2020-05-04 12:42
 */
@Getter
@AllArgsConstructor
public enum  DistributeTargetEnum {

    SINGLE(1,"单用户"),
    MULTI(2,"多用户");

    /**分发目标编码*/
    private Integer code;
    /**分发目标描述*/
    private String desc;

    public static DistributeTargetEnum of(String code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " 该code码不存在！"));
    }
}
