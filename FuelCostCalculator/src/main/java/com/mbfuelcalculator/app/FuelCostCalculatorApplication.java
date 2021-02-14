package com.mbfuelcalculator.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mbfuelcalculator.app.repo.mysql.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class FuelCostCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelCostCalculatorApplication.class, args);
	}

}
