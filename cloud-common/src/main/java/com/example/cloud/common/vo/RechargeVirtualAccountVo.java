package com.example.cloud.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yuhongguang
 * @date 2022/7/29 16:30
 */
@Data
public class RechargeVirtualAccountVo {

    private Integer[] ids;//账户ID
    private BigDecimal rechargeMoney;//充值金额
    private boolean openRideMonth;//乘车包月
    private Date overRideMonthDate;//乘车包月到期时间
    private boolean openRepastMonth;//就餐包月
    private Date overRepastMonthDate;//就餐包月到期时间
    private boolean openParkMonth;//停车包月
    private Date overParkMonthDate;//停车包月到期时间
    private int rideTime;//乘车次数
    private int repastTime;//就餐次数
    private int parkTime;//停车次数
}
