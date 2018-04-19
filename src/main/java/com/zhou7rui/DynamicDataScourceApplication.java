package com.zhou7rui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhou7rui.dao")
public class DynamicDataScourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDataScourceApplication.class, args);
	}
}
