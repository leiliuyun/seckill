package com.shenfei.service.impl;

import com.shenfei.dao.SeckillDao;
import com.shenfei.dao.SuccessKilledDao;
import com.shenfei.domain.Seckill;
import com.shenfei.domain.SuccessKilled;
import com.shenfei.dto.Exposer;
import com.shenfei.dto.SeckillExecution;
import com.shenfei.enums.SeckillStatEnum;
import com.shenfei.exception.RepeatKillException;
import com.shenfei.exception.SeckillCloseException;
import com.shenfei.exception.SeckillException;
import com.shenfei.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String slat = "lasjl1juwlkashjl1uo2i1j2j1098209808makjslkj*^*^*%";
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        } else {
            Date startTime = seckill.getStartTime();
            Date endTime = seckill.getEndTime();
            Date now = new Date();
            if (now.before(startTime) || now.after(endTime)) {
                return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
            } else {
                return new Exposer(true, getMD5(seckillId), seckillId);
            }
        }
    }

    private String getMD5(long seckillId) {

        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatKillException, SeckillCloseException, SeckillException {

        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill date rewrite");
        } else {
            try {
                //执行秒杀逻辑
                //减库存
                Date nowTime = new Date();
                int updatenumber = seckillDao.reduceNumber(seckillId, nowTime);
                if (updatenumber <= 0) {
                    //没有更新记录
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //记录购买行为
                    int insertcount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                    if (insertcount <= 0) {
                        throw new RepeatKillException("seckill repeated");
                    } else {
                        //成功秒杀
                        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                        return new SeckillExecution(seckillId, SeckillStatEnum.SUCESS, successKilled);
                    }
                }
            } catch (SeckillCloseException e1) {
                throw e1;
            } catch (RepeatKillException e2) {
                throw e2;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new SeckillException("seckill inner error:" + e.getMessage());
            }
        }
    }
}
