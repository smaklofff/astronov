package com.askme.astronov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableRetry
//@EnableCaching
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ConfigurationPropertiesScan
public class AstronovApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstronovApplication.class, args);
		log.info("""
                \s
                 -----------------------------------
                |                                   |
                |   Astronov Application Started    |
                |                                   |
                 -----------------------------------
                \s"""
		);
	}
}
