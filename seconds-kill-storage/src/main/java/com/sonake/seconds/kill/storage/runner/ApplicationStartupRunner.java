package com.sonake.seconds.kill.storage.runner;

import com.sonake.seconds.kill.storage.entity.Goods;
import com.sonake.seconds.kill.storage.service.GoodService;
import com.sonake.seconds.kill.storage.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/3 11:40
 * @description：
 * @version:
 */
@Component
@Slf4j
public class ApplicationStartupRunner implements ApplicationRunner {
    @Autowired
    private GoodService goodsService;
    @Autowired
    private RedisService redisService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Goods> list = goodsService.list();
        list.forEach(goods -> {
            redisService.put(goods.getGoodsName(),goods.getStore(),20);
        });
        log.info("商品库存初始化至redis完毕");
    }
}
