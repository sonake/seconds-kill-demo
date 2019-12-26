package com.sonake.seconds.kill.demo.service;

import com.sonake.seconds.kill.demo.config.RabbitMQConfig;
import com.sonake.seconds.kill.demo.domain.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 14:49
 * @description：
 * @version:
 */
@Service
@Slf4j
public class MQOrderService {
    @Autowired
    private OrderService orderService;

    /**
     * 监听订单消息队列，并消费
     *
     * @param orders
     */
    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void createOrder(Orders orders) {
        log.info("收到订单消息，订单用户为：{}，商品名称为：{}", orders.getOrderUser(), orders.getOrderName());
        /**
         * 调用数据库orderService创建订单信息
         */
        orderService.save(orders);
    }
}
