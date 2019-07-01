package com.qf.springboot_mail;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@DubboComponentScan("com.qf.service.impl")
public class SpringbootMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMailApplication.class, args);
    }

}
