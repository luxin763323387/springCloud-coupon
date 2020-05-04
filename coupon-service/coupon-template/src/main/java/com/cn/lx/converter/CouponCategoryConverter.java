package com.cn.lx.converter;

import com.cn.lx.constant.CouponCategoryEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 优惠券分类枚举属性转换器
 * AttributeConverter<X, Y>
 * X: 是实体属性的类型
 * Y: 是数据库字段的类型
 * @author StevenLu
 * @date 2020-05-04 14:59
 */
@Converter
public class CouponCategoryConverter implements AttributeConverter<CouponCategoryEnum,String> {

    /***
     * todo 插入和更新数据库
     * 将实体属性X转换为Y存储到数据库中, 插入和更新时执行的动作
     */
    @Override
    public String convertToDatabaseColumn(CouponCategoryEnum attribute) {
        return attribute.getCode();
    }

    /**
     * todo 查询
     * 将数据库中的字段Y转换为实体属性X, 查询操作时执行的动作
     */
    @Override
    public CouponCategoryEnum convertToEntityAttribute(String s) {
        return CouponCategoryEnum.of(s);
    }
}
