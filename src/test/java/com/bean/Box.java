package com.bean;

public class Box {

	private String name;
	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String toString() {
		return "Box [name=" + name + ", car=" + car + "]";
	}

}
