package com.shenfei.dao;


import com.shenfei.domain.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() {
        int count = seckillDao.reduceNumber(1000, new Date());
        System.out.println(count);
    }


    @Test
    public void queryById() {
        Seckill seckill = seckillDao.queryById(1000);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    @Test
    public void queryAll() {
        List<Seckill> list = seckillDao.queryAll(0, 100);
        for (Seckill seck : list) {
            System.out.println(seck);
        }
    }
}