package com.sonake.seconds.kill.order.controller;

import com.sonake.seconds.kill.order.configure.RabbitMqConfigure;
import com.sonake.seconds.kill.order.entity.Orders;
import com.sonake.seconds.kill.order.entity.Trade;
import com.sonake.seconds.kill.order.service.OrderService;
import com.sonake.seconds.kill.order.service.RedisService;
import com.sonake.seconds.kill.order.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 13:35
 * @description：
 * @version:
 */
@RestController
@Slf4j
public class SecController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TradeService tradeService;


    /**
     * 使用redis+消息队列进行秒杀实现
     *
     * @param username
     * @param goodsName
     * @return
     */
    @RequestMapping("/sec")
    @ResponseBody
    public String sec(@RequestParam(value = "username") String username, @RequestParam(value = "goodsName") String goodsName) {
        log.info("参加秒杀的用户是：{}，秒杀的商品是：{}", username, goodsName);
        String message = null;
        //调用redis给相应商品库存量减一
        Long decrByResult = redisService.decrBy(goodsName);
        if (decrByResult >= 0) {
            /**
             * 说明该商品的库存量有剩余，可以进行下订单操作
             */
            log.info("用户：{}秒杀该商品：剩余{}库存，可以进行下订单操作", username, decrByResult, goodsName);
            //发消息给库存消息队列，将库存数据减一
            Trade t = new Trade();
            t.setGoodsname(goodsName);
            t.setUsername(username);
            t.setTrade("顺丰");
            rabbitTemplate.convertAndSend(RabbitMqConfigure.STORY_EXCHANGE, RabbitMqConfigure.STORY_ROUTING_KEY, t);

            //发消息给订单消息队列，创建订单
            Orders orders = new Orders();
            orders.setOrderName(goodsName);
            orders.setOrderUser(username);
            rabbitTemplate.convertAndSend(RabbitMqConfigure.ORDER_EXCHANGE, RabbitMqConfigure.ORDER_ROUTING_KEY, orders);
            message = "用户" + username + "秒杀" + goodsName + "成功";
        } else {
            /**
             * 说明该商品的库存量没有剩余，直接返回秒杀失败的消息给用户
             */
            log.info("用户：{}秒杀时商品的库存量没有剩余,秒杀结束", username);
            message = username + "商品的库存量没有剩余,秒杀结束";
        }
        return message;
    }


}
