package com.sonake.seconds.kill.order.service;

import com.sonake.seconds.kill.order.fallback.IStorageServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 9:41
 * @description：定义system服务的接口
 * @version: 1.0
 */
@FeignClient(value = "seconds-kill-storage", contextId = "IStorageServiceClient", fallbackFactory = IStorageServiceFallback.class)
public interface IStorageService {

    @GetMapping("decrByStore")
    void decrByStore(@RequestParam String goodsName);
}
