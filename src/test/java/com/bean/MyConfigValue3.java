package com.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyConfigValue3 {
	private String id;
	@Value("#{myConfigValue1.key1Name}")
	private String key3Name;
	@Value("#{myConfigValue2.key2Value}")
	private String key3Value;

	@Value("${noExistValue:defaultValue_1}")
	private String defaultValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey3Name() {
		return key3Name;
	}

	public void setKey3Name(String key3Name) {
		this.key3Name = key3Name;
	}

	public String getKey3Value() {
		return key3Value;
	}

	public void setKey3Value(String key3Value) {
		this.key3Value = key3Value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String toString() {
		return "MyConfigValue3 [id=" + id + ", key3Name=" + key3Name + ", key3Value=" + key3Value + ", defaultValue="
				+ defaultValue + "]";
	}

}
