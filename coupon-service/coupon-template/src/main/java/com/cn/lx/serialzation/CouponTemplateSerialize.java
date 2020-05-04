package com.cn.lx.serialzation;

import com.alibaba.fastjson.JSON;
import com.cn.lx.entity.CouponTemplate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author StevenLu
 * @date 2020-05-04 15:24
 */
public class CouponTemplateSerialize extends JsonSerializer<CouponTemplate> {
    @Override
    public void serialize(CouponTemplate couponTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        // 开始序列化对象
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("id", couponTemplate.getId().toString());
        jsonGenerator.writeStringField("name", couponTemplate.getName());
        jsonGenerator.writeStringField("logo", couponTemplate.getLogo());
        jsonGenerator.writeStringField("desc", couponTemplate.getDesc());
        jsonGenerator.writeStringField("category",
                couponTemplate.getCategory().getDesc());
        jsonGenerator.writeStringField("productLine",
                couponTemplate.getProductLine().getDesc());
        jsonGenerator.writeStringField("count", couponTemplate.getCount().toString());
        jsonGenerator.writeStringField("createTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(couponTemplate.getCreateTime()));
        jsonGenerator.writeStringField("userId", couponTemplate.getUserId().toString());
        jsonGenerator.writeStringField("key",
                couponTemplate.getKey() + String.format("%04d", couponTemplate.getId()));
        jsonGenerator.writeStringField("target",
                couponTemplate.getTarget().getDesc());
        jsonGenerator.writeStringField("rule",
                JSON.toJSONString(couponTemplate.getRule()));

        // 结束序列化对象
        jsonGenerator.writeEndObject();
    }
}
