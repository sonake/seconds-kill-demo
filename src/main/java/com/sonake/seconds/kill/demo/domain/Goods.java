package com.sonake.seconds.kill.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 11:50
 * @description：
 * @version:
 */
@TableName("goods")
@Data
public class Goods implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;
    private String goodsName;
    private int store;
}
