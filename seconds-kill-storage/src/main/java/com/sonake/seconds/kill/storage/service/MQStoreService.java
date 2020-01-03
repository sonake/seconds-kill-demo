package com.sonake.seconds.kill.storage.service;

import com.sonake.seconds.kill.storage.configure.RabbitMqConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/3 11:21
 * @description：
 * @version:
 */
@Slf4j
@Service
public class MQStoreService {
    @Autowired
    private GoodService goodService;

    /**
     * 监听库存消息队列，并消费
     *
     * @param goodsName
     */
    @RabbitListener(queues = RabbitMqConfigure.STORY_QUEUE)
    public void decrByStock(String goodsName) {
        log.info("库存消息队列收到的消息商品信息是：{}", goodsName);
        /**
         * 调用数据库service给数据库对应商品库存减一
         */
        //int s =Integer.valueOf("ssss");
        goodService.decrByStore(goodsName);
    }
}
