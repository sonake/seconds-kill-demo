package com.sonake.seconds.kill.order.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.sonake.seconds.kill.order.configure.RabbitMqConfigure;
import com.sonake.seconds.kill.order.entity.Orders;
import com.sonake.seconds.kill.order.entity.Trade;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RedisService redisService;
    /**
     * 监听订单消息队列，并消费
     *
     * @param message
     */
    @RabbitListener(queues = RabbitMqConfigure.ORDER_QUEUE)
    public void createOrder(Orders orders) throws Exception {
        // 获取消息Id
        //String messageId = message.getMessageProperties().getMessageId();
        //String msg = new String(message.getBody(), "UTF-8");
        //Orders orders = JSON.parseObject(msg,Orders.class);
        //log.info("订单消息队列获取生产者消息ID：{}" , messageId);
        log.info("收到订单消息，订单用户为：{}，商品名称为：{}", orders.getOrderUser(), orders.getOrderName());
        //if (messageId == null) {
        //    return;
        //}
        // 判断消息是否已经被消费,redis存在则证明已经被消费，不需要重复消费
        //String messageIdByRedis = redisService.getString(messageId);
        //log.info("redis消息ID：{}",messageIdByRedis);
       // if(messageIdByRedis!=null){
        //    return;
        //}
        /**
         * 调用数据库orderService创建订单信息
         */
        orderService.save(orders);
        //log.info("执行结束，将消息id存指redis，用去处理判断是否重复消费");
        // 手动ack
        //Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手动签收
        //channel.basicAck(deliveryTag, false);
        //此处的过期时间需要仔细斟酌，需要计算超时时间，60只是大概写了下
        //redisService.put(messageId,"已消费",60);

    }

    @RabbitListener(queues = RabbitMqConfigure.STORY_QUEUE)
    @GlobalTransactional
    public void decrByStore(Trade t) {
        try {
            // 获取消息Id
            //String messageId = message.getMessageProperties().getMessageId();
            //String msg = new String(message.getBody(), "UTF-8");
            //Trade t = JSON.parseObject(msg,Trade.class);
            //log.info("物流消息队列获取生产者消息ID：{}" , messageId);
            log.info("物流消息队列收到的消息商品信息是：{},{},{}", t.getGoodsname(),t.getUsername(),t.getTrade());
            //if (messageId == null) {
            //    return;
            //}
            // 判断消息是否已经被消费,redis存在则证明已经被消费，不需要重复消费
            //String messageIdByRedis = redisService.getString(messageId);
            //log.info("redis消息ID：{}",messageIdByRedis);
            //if(messageIdByRedis!=null){
            //    return;
            //}
            log.info("当前 XID: {}", RootContext.getXID());
            /**
             * 调用数据库创建物流信息
             */
            tradeService.saves(t);
            storageService.decrByStore(t.getGoodsname());
            throw new RuntimeException("抛个异常");
            //log.info("执行结束，将消息id存指redis，用去处理判断是否重复消费");
            // 手动ack
            // Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            // 手动签收
            //channel.basicAck(deliveryTag, false);
            //此处的过期时间需要仔细斟酌，需要计算超时时间，60只是大概写了下
            //redisService.put(messageId,"已消费",60);

        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 死信队列,消息消费失败后进入死信队列，手动确认
     *
     * @param message
     */
    //@RabbitListener(queues = RabbitMqConfigure.LIND_DEAD_QUEUE)
    public void dealSubscribe(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        //System.out.println("Dead Subscriber:" + new String(message.getBody(), "UTF-8"));
    }

}
