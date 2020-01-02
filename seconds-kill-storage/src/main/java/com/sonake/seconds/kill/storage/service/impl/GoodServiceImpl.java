package com.sonake.seconds.kill.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonake.seconds.kill.storage.dao.GoodDao;
import com.sonake.seconds.kill.storage.entity.Goods;
import com.sonake.seconds.kill.storage.service.GoodService;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:37
 * @description：
 * @version:
 */
@Service("goodService")
public class GoodServiceImpl extends ServiceImpl<GoodDao, Goods> implements GoodService {
}
