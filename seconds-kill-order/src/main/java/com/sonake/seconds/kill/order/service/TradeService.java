package com.sonake.seconds.kill.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sonake.seconds.kill.order.entity.Orders;
import com.sonake.seconds.kill.order.entity.Trade;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:28
 * @description：
 * @version:
 */
public interface TradeService extends IService<Trade> {
    void saves(Trade t);
    void secByin(Trade t);
}
