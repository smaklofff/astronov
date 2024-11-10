package com.askme.astronov;

import com.askme.astronov.utils.SslPemCert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableRetry
@EnableScheduling
@EnableFeignClients
@EnableAspectJAutoProxy
@SpringBootApplication
public class AstronovApplication {


	public static void main(String[] args) {
//		SslPemCert.setSslCert();
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
