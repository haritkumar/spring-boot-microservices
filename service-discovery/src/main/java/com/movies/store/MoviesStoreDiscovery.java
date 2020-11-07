package com.movies.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MoviesStoreDiscovery {

	public static void main(String[] args) {
		SpringApplication.run(MoviesStoreDiscovery.class, args);
	}

}
