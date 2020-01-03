package com.sonake.seconds.kill.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecondsKillOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondsKillOrderApplication.class, args);
    }

}
