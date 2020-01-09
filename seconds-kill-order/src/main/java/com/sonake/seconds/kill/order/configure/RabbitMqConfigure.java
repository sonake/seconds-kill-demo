package com.sonake.seconds.kill.order.configure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/3 11:11
 * @description：
 * @version:
 */
@Configuration
public class RabbitMqConfigure {
    //库存交换机
    public static final String STORY_EXCHANGE = "STORY_EXCHANGE";

    //订单交换机
    public static final String ORDER_EXCHANGE = "ORDER_EXCHANGE";

    //死信交换机
    public static final String LIND_DL_EXCHANGE = "LIND_DL_EXCHANGE";

    //库存队列
    public static final String STORY_QUEUE = "STORY_QUEUE";

    //订单队列
    public static final String ORDER_QUEUE = "ORDER_QUEUE";

    //死信队列
    public static final String LIND_DEAD_QUEUE = "LIND_DEAD_QUEUE";

    //库存路由键
    public static final String STORY_ROUTING_KEY = "STORY_ROUTING_KEY";

    //订单路由键
    public static final String ORDER_ROUTING_KEY = "ORDER_ROUTING_KEY";

    //死信路由键
    public static final String LIND_DEAD_ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    //创建库存交换机
    @Bean
    public Exchange getStoryExchange() {
        return ExchangeBuilder.directExchange(STORY_EXCHANGE).durable(true).build();
    }

    //创建库存队列
    @Bean
    public Queue getStoryQueue() {

        return QueueBuilder.durable(STORY_QUEUE)
                //设置死信交换机
                .withArgument("x-dead-letter-exchange",LIND_DL_EXCHANGE)
                //毫秒
                 .withArgument("x-message-ttl",2000)
                //设置死信路由键
                 .withArgument("x-dead-letter-routing-key",LIND_DEAD_ROUTING_KEY).build();
        //return new Queue(STORY_QUEUE);

    }

    //库存交换机和库存队列绑定
    @Bean
    public Binding bindStory() {
        return BindingBuilder.bind(getStoryQueue()).to(getStoryExchange()).with(STORY_ROUTING_KEY).noargs();
    }

    //创建订单队列
    @Bean
    public Queue getOrderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    //创建订单交换机
    @Bean
    public Exchange getOrderExchange() {
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).durable(true).build();
    }

    //订单队列与订单交换机进行绑定
    @Bean
    public Binding bindOrder() {
        return BindingBuilder.bind(getOrderQueue()).to(getOrderExchange()).with(ORDER_ROUTING_KEY).noargs();
    }

    //创建死信交换机
    @Bean
    public Exchange getDeadExchange() {
        return ExchangeBuilder.directExchange(LIND_DL_EXCHANGE).durable(true).build();
    }

    //创建死信队列
    @Bean
    public Queue getDeadQueue() {
        return new Queue(LIND_DEAD_QUEUE);
    }

    //死信队列和死信交换机绑定
    @Bean
    public Binding bindDead() {
        return BindingBuilder.bind(getDeadQueue()).to(getDeadExchange()).with(LIND_DEAD_ROUTING_KEY).noargs();
    }
}
