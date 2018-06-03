package com.bean;

public class MyConfigValue {
	private String id;
	private String key1Name;
	private String key1Value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey1Name() {
		return key1Name;
	}

	public void setKey1Name(String key1Name) {
		this.key1Name = key1Name;
	}

	public String getKey1Value() {
		return key1Value;
	}

	public void setKey1Value(String key1Value) {
		this.key1Value = key1Value;
	}

	public String toString() {
		return "MyConfigValue [id=" + id + ", key1Name=" + key1Name + ", key1Value=" + key1Value + "]";
	}

}
