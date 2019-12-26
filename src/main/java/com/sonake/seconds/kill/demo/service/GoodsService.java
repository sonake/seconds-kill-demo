package com.sonake.seconds.kill.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sonake.seconds.kill.demo.domain.Goods;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 11:49
 * @description：
 * @version:
 */
public interface GoodsService extends IService<Goods> {
    String secData(String username,String goodsName);

    void decrByStore(String goodsName);
}
