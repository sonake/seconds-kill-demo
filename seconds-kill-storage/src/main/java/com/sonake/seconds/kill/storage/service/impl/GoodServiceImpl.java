package com.sonake.seconds.kill.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonake.seconds.kill.storage.dao.GoodDao;
import com.sonake.seconds.kill.storage.entity.Goods;
import com.sonake.seconds.kill.storage.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:37
 * @description：
 * @version:
 */
@Service("goodService")
@Slf4j
public class GoodServiceImpl extends ServiceImpl<GoodDao, Goods> implements GoodService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrByStore(String goodsName) {
        //1、库存减一
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGoodsName, goodsName);
        Goods goods = baseMapper.selectOne(queryWrapper);
        goods.setStore(goods.getStore() - 1);
        goods.setGoodsName(goods.getGoodsName());
        updateById(goods);
    }
}
