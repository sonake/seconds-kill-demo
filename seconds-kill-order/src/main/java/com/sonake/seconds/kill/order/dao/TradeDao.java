package com.sonake.seconds.kill.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sonake.seconds.kill.order.entity.Orders;
import com.sonake.seconds.kill.order.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:25
 * @description：
 * @version:
 */
@Mapper
public interface TradeDao extends BaseMapper<Trade> {
}
