package com.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bean.Car;

@Configuration
public class MyCarConfigurationBeans {

	@Bean(name = "car")
	public Car buildCar() {
		Car car = new Car();
		car.setBrand("test");
		car.setMaxSpeed(200);
		return car;
	}
}
