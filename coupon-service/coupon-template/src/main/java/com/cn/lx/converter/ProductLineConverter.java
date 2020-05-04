package com.cn.lx.converter;

import com.cn.lx.constant.ProductLineEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 流水线转换器
 * @author StevenLu
 * @date 2020-05-04 15:11
 */
@Converter
public class ProductLineConverter
        implements AttributeConverter<ProductLineEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductLineEnum productLine) {
        return productLine.getCode();
    }

    @Override
    public ProductLineEnum convertToEntityAttribute(Integer code) {
        return ProductLineEnum.of(code);
    }
}
