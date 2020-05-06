package com.cn.lx.vo;

import com.cn.lx.constant.PeriodTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 优惠券规则对象定义
 * @author StevenLu
 * @date 2020-05-04 13:34
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRule {

    /** 优惠券过期规则 */
    private Expiration expiration;

    /** 折扣 */
    private Discount discount;

    /** 每个人最多几张的限制 */
    private Integer limitation;

    /** 使用范围:地域 + 商品类型*/
    private Usage usage;

    /**
     * 权重
     * 可以和哪些优惠券叠加用，同一类的优惠券一定不能叠加使用
     * list[],优惠券的唯一编码
     *
     * */
    private String weight;

    public boolean validate(){
        return expiration.validate() && discount.validate()
                && limitation > 0 && usage.validate()
                && StringUtils.isNotEmpty(weight);
    }


    /**
     * 有效期规则
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Expiration{

        /** 有效规则，对于 PeriodType 的code字段*/
        private Integer periodCode;

        /**
         * todo 只针对一类
         * 有效间隔: 只对变动性有效期有效
         * */
        private Integer gap;

        /**优惠券模版失效日期:2类规格都有效*/
        private Long deadline;

        boolean validate(){
            return null != PeriodTypeEnum.of(periodCode) && gap > 0 && deadline > 0;
        }
    }

    /**
     * 折扣，需要和类型配合
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Discount{

        /**
         * 额度:
         * 满减满100-20(20), 折扣8.5折扣(85)，立减(10)
         * */
        private Integer quota;

        /**
         * 基准：需要满多少才可以使用
         */
        private Integer base;

        boolean validate(){
            return quota > 0 && base > 0;
        }
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Usage{

        /** 省份 */
        private String province;

        /** 城市*/
        private String city;

        /** 商品类型，list[文娱，生鲜，家具，全品类]*/
        private String goodsType;

        boolean validate(){
            return StringUtils.isNotEmpty(province)
                    && StringUtils.isNotEmpty(city)
                    && StringUtils.isNotEmpty(goodsType);
        }
    }
}
