package com.enjoytrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.enjoytrip.mapper")
@SpringBootApplication
public class EnjoyTripApplication {
	public static void main(String[] args) {
		SpringApplication.run(EnjoyTripApplication.class, args);
	}
}
