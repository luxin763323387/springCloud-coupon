package com.cn.lx.dao;

import com.cn.lx.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author StevenLu
 * @date 2020-05-04 15:36
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate,Integer> {

    /**
     * 根据模版名称查询模版
     * where name = '';
     * @param name
     * @return
     */
    CouponTemplate findByName (String name);

    /**
     * 根据available 和 expired 标记查询模版记录
     * where available = ? and expired = ?;
     * @param available 可用状态
     * @param expired 是否过期
     * @return
     */
    List<CouponTemplate> findAllByAvailableAndExpired(
            Boolean available, Boolean expired
    );

    /**
     * 根据available 和 expired 标记查询模版记录
     * where expired = ?;
     * @param expired
     * @return
     */
    List<CouponTemplate> findAllByExpired(Boolean expired);
}
