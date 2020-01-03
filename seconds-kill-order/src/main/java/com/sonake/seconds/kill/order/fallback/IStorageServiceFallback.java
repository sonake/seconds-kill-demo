package com.sonake.seconds.kill.order.fallback;

import com.sonake.seconds.kill.order.service.IStorageService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 9:57
 * @description：回退方法
 * @version: 1.0
 */
@Slf4j
@Component
public class IStorageServiceFallback implements FallbackFactory<IStorageService> {

    @Override
    public IStorageService create(Throwable throwable) {
        return name -> {
            log.error("调用seconds-kill-storagem服务出错", throwable);
        };
    }
}
