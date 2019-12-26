package com.sonake.seconds.kill.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonake.seconds.kill.demo.domain.Goods;
import com.sonake.seconds.kill.demo.domain.Orders;
import com.sonake.seconds.kill.demo.mapper.GoodsMapper;
import com.sonake.seconds.kill.demo.service.GoodsService;
import com.sonake.seconds.kill.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 13:35
 * @description：
 * @version:
 */
@Service("goodsService")
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private OrderService orderService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String secData(String username,String goodsName) {
        log.info("参加秒杀的用户是：{}，秒杀的商品是：{}", username, goodsName);
        String message = null;
        //查找该商品库存
        Integer storeCount = this.count(new LambdaQueryWrapper<Goods>().eq(Goods::getGoodsName,goodsName));
        log.info("用户：{}参加秒杀，当前商品库存量是：{}", username, storeCount);
        if (storeCount > 0) {
            /**
             * 还有库存，可以进行继续秒杀，库存减一,下订单
             */
            //1、库存减一
            LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Goods::getGoodsName, goodsName);
            Goods goods = baseMapper.selectOne(queryWrapper);
            goods.setStore(goods.getStore() - 1);
            updateById(goods);
            //2、下订单
            Orders orders = new Orders();
            orders.setOrderUser(username);
            orders.setOrderName(goodsName);
            orderService.save(orders);
            log.info("用户：{}.参加秒杀结果是：成功", username);
            message = username + "参加秒杀结果是：成功";
        }else {
            log.info("用户：{}.参加秒杀结果是：秒杀已经结束", username);
            message = username + "参加秒杀活动结果是：秒杀已经结束";
        }
        return message;
    }

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
