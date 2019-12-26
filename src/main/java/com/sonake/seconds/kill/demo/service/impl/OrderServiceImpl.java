package com.sonake.seconds.kill.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonake.seconds.kill.demo.domain.Orders;
import com.sonake.seconds.kill.demo.mapper.OrderMapper;
import com.sonake.seconds.kill.demo.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 13:49
 * @description：
 * @version:
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
}
