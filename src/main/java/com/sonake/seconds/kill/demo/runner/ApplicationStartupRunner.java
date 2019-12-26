package com.sonake.seconds.kill.demo.runner;

import com.sonake.seconds.kill.demo.domain.Goods;
import com.sonake.seconds.kill.demo.service.GoodsService;
import com.sonake.seconds.kill.demo.service.RedisService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 11:39
 * @description：项目启动数据初始化
 * @version: 1.0
 */
@Component
@Slf4j
public class ApplicationStartupRunner implements ApplicationRunner {
    @Autowired
    private GoodsService goodsService;
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
