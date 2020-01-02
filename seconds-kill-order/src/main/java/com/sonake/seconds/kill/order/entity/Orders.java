package com.sonake.seconds.kill.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/2 11:52
 * @description：订单
 * @version: 1.0
 */
@Data
@TableName("orders")
public class Orders implements Serializable {
    private static final long serialVersionUID = -4678930336129290335L;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String orderName;
    private String orderUser;
}
