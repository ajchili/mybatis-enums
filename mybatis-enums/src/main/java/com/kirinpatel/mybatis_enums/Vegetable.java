package com.kirinpatel.mybatis_enums;

public enum Vegetable {
	POTATO("Potato"),
	potato("potato"),
	TOMATO("Tomato"),
	tomato("tomato");

	private final String value;

	private Vegetable(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
