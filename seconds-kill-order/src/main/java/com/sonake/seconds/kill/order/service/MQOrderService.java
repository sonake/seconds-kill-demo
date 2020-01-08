package com.sonake.seconds.kill.order.service;

import com.rabbitmq.client.Channel;
import com.sonake.seconds.kill.order.configure.RabbitMqConfigure;
import com.sonake.seconds.kill.order.entity.Orders;
import com.sonake.seconds.kill.order.entity.Trade;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private TradeService tradeService;
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
        orderService.save(orders);


    }

    @RabbitListener(queues = RabbitMqConfigure.STORY_QUEUE)
    @GlobalTransactional
    public void decrByStore(Trade t, Channel channel, Message message)throws IOException, InterruptedException {
        try {
            log.info("物流消息队列收到的消息商品信息是：{},{},{}", t.getGoodsname(),t.getUsername(),t.getTrade());
            log.info("当前 XID: {}", RootContext.getXID());
            /**
             * 调用数据库创建物流信息
             */
            tradeService.saves(t);
            storageService.decrByStore(t.getGoodsname());
            throw new RuntimeException("错了");
        }catch (Exception e){
            throw new RuntimeException("错了");
        }
    }


    /**
     * 死信队列,消息消费失败后进入死信队列，手动确认
     *
     * @param message
     */
    //@RabbitListener(queues = RabbitMqConfigure.LIND_DEAD_QUEUE)
    public void dealSubscribe(Message message, Channel channel) throws IOException {
        System.out.println("Dead Subscriber:" + new String(message.getBody(), "UTF-8"));
    }

}
