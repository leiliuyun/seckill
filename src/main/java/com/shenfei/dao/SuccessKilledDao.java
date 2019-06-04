package com.shenfei.dao;

import com.shenfei.domain.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    /*
     * 插入购买明细，过滤重复
     * */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /*
     * 根据id查询SucessKilled
     * */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
