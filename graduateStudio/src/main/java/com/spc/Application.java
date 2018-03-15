/**
 * 
 */
package com.spc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lc
 * 启动类，对于每一个微服务都一样
 */
@SpringBootApplication
@MapperScan("com.spc")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
