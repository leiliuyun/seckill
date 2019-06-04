package com.shenfei.service;
import com.shenfei.domain.Seckill;
import com.shenfei.dto.Exposer;
import com.shenfei.dto.SeckillExecution;
import com.shenfei.exception.RepeatKillException;
import com.shenfei.exception.SeckillCloseException;
import com.shenfei.exception.SeckillException;
import java.util.List;
//站在使用者的角度设计接口
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     */
    List<Seckill> getSeckillList();
    /**
     * 查询所有秒杀记录
     */
    Seckill getById(long seckillId);
    /**
     * 秒杀开启输出秒杀接口地址，否则输出秒杀时间和系统时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);
    /**
     * 执行秒杀操作
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws RepeatKillException,SeckillCloseException,SeckillException;
}
