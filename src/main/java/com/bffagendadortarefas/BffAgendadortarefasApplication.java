package com.bffagendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class BffAgendadortarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffAgendadortarefasApplication.class, args);
	}

}
