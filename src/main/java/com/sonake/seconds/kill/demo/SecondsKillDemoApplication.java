package com.sonake.seconds.kill.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sonake.seconds.kill.demo.mapper")
public class SecondsKillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondsKillDemoApplication.class, args);
    }

}
