package ru.nms.labs.data_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DataServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServerApplication.class, args);
	}

}
