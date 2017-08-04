package com.pangpang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PoiDownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiDownloadApplication.class, args);
	}
}
