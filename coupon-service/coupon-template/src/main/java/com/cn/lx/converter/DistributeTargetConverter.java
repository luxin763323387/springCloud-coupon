package com.cn.lx.converter;

import com.cn.lx.constant.DistributeTargetEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 产品线枚举属性转换
 * @author StevenLu
 * @date 2020-05-04 15:09
 */
@Converter
public class DistributeTargetConverter
        implements AttributeConverter<DistributeTargetEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DistributeTargetEnum distributeTarget) {
        return distributeTarget.getCode();
    }

    @Override
    public DistributeTargetEnum convertToEntityAttribute(Integer code) {
        return DistributeTargetEnum.of(code);
    }
}
