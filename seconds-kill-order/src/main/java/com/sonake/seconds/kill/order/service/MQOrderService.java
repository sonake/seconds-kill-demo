package com.sonake.seconds.kill.order.service;

import com.sonake.seconds.kill.order.configure.RabbitMqConfigure;
import com.sonake.seconds.kill.order.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/3 11:20
 * @description：
 * @version:
 */
@Service
@Slf4j
public class MQOrderService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private IStorageService storageService;
    /**
     * 监听订单消息队列，并消费
     *
     * @param orders
     */
    @RabbitListener(queues = RabbitMqConfigure.ORDER_QUEUE)
    public void createOrder(Orders orders) {
        log.info("收到订单消息，订单用户为：{}，商品名称为：{}", orders.getOrderUser(), orders.getOrderName());
        /**
         * 调用数据库orderService创建订单信息
         */
        //int s =Integer.valueOf("ssss");
        orderService.save(orders);


    }

    @RabbitListener(queues = RabbitMqConfigure.ORDER_QUEUE)
    public void decrByStore(String goodsName) {
        log.info("库存消息队列收到的消息商品信息是：{}", goodsName);
        /**
         * 调用数据库orderService创建订单信息
         */
        //int s =Integer.valueOf("ssss");
        storageService.decrByStore(goodsName);


    }
}
