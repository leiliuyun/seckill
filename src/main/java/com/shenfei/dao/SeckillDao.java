package com.shenfei.dao;

import com.shenfei.domain.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /*
     * 减库存
     * */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /*
     * 根据id查询秒杀对象
     * */
    Seckill queryById(long seckillId);

    /*
     * 根据偏移量查询秒杀商品列表
     * */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
