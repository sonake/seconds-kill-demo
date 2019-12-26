package com.sonake.seconds.kill.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/26 13:42
 * @description：
 * @version:
 */
@TableName("orders")
@Data
public class Orders {
    @TableId(type = IdType.AUTO)
    private int id;
    private String orderName;
    private String orderUser;
}
