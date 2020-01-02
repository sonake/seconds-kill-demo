package com.sonake.seconds.kill.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 12:18
 * @description：库存
 * @version: 1.0
 */
@Data
@TableName("goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = 6548838684842403207L;
    @TableId(type = IdType.AUTO)
    private int id;
    private String goodsName;
    private int store;
}
