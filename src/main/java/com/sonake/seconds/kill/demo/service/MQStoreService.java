package com.sonake.seconds.kill.demo.service;

import com.sonake.seconds.kill.demo.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 14:47
 * @description：
 * @version:
 */
@Slf4j
@Service
public class MQStoreService {

    @Autowired
    private GoodsService goodsService;

    /**
     * 监听库存消息队列，并消费
     *
     * @param goodsName
     */
    @RabbitListener(queues = RabbitMQConfig.STORY_QUEUE)
    public void decrByStock(String goodsName) {
        log.info("库存消息队列收到的消息商品信息是：{}", goodsName);
        /**
         * 调用数据库service给数据库对应商品库存减一
         */
        goodsService.decrByStore(goodsName);
    }
}
