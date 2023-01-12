package com.magician.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MusicApplication {

	public static void main(String[] args) {

		SpringApplication.run(MusicApplication.class, args);
	}

}
