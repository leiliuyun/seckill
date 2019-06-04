package com.shenfei.dto;
import com.shenfei.domain.SuccessKilled;
import com.shenfei.enums.SeckillStatEnum;
/**
 * 封装秒杀执行后的结果
 */
public class SeckillExecution {
    private long seckillId;
    //秒杀执行结果状态
    private int state;
    //状态信息
    private String stateInfo;
    //秒杀成功对象
    private SuccessKilled successKilled;
    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.successKilled = successKilled;
    }
    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }
    public long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getStateInfo() {
        return stateInfo;
    }
    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }
    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
