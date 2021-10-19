package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com"})
public class FairloApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FairloApplication.class, args);
	}
}
