package com.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.context.TestAnnotationApplicationContext;

public class Car {
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);
	private String brand;
	private String color;
	private int maxSpeed;
	private String name;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void myInit() {
		log.info("car init method");
	}

	public void myDestory() {
		log.info("car destory method");
	}

	@PostConstruct
	public void myPostConstruct() {
		log.info("car postConstruct process");
	}

	@PreDestroy
	public void myPreDestroy() {
		log.info("car preDestroy process");
	}

	public Car() {
		super();
		log.info("car init construct ");
	}

	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", maxSpeed=" + maxSpeed + ", name=" + name + "]";
	}

}
