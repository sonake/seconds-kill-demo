package com.sonake.seconds.kill.storage.controller;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/3 17:58
 * @description：
 * @version:
 */

import com.sonake.seconds.kill.storage.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StorageController {
    @Autowired
    private GoodService goodService;
    /**
     * 供远程调用
     * @param goodsName
     * @return
     */
    @GetMapping("decrByStore")
    public void decrByStore(String goodsName) {
        log.info("/库存减少接口调用");
        goodService.decrByStore(goodsName);
    }
}
