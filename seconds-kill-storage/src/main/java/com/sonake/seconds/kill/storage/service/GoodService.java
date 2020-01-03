package com.sonake.seconds.kill.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sonake.seconds.kill.storage.entity.Goods;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:37
 * @description：
 * @version:
 */
public interface GoodService extends IService<Goods> {

    void decrByStore(String goodsName);
}
