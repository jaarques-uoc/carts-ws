package com.jaarquesuoc.shop.carts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartsApplication.class, args);
	}

}
