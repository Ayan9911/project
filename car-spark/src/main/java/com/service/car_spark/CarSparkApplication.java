package com.service.car_spark;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit  // Enables RabbitMQ listeners
@SpringBootApplication
public class CarSparkApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarSparkApplication.class, args);
	}
}
