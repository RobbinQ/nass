package com.robin.nass;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName NASSApplication
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/19 12:01
 */
@SpringBootApplication
@Slf4j
public class NASSApplication {
    public static void main(String[] args) {
        SpringApplication.run(NASSApplication.class,args);
        log.info("项目启动成功！");
    }
}
