package com.sonake.seconds.kill.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonake.seconds.kill.order.dao.OrderDao;
import com.sonake.seconds.kill.order.entity.Orders;
import com.sonake.seconds.kill.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:30
 * @description：
 * @version:
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Orders> implements OrderService {

}
