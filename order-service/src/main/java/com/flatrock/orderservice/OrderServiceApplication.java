package com.flatrock.orderservice;

import com.flatrock.orderservice.mapper.OrderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public OrderMapper orderMapper() { return new OrderMapper(modelMapper()); }

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
