package com.kirinpatel.mybatis_enums;

public class Test {
	public String id;
	public Vegetable vegetable;

	public Test(String id, Vegetable vegetable) {
		this.id = id;
		this.vegetable = vegetable;
	}

	public String getId() {
		return id;
	}

	public Vegetable getVegetable() {
		return vegetable;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVegetable(Vegetable vegetable) {
		this.vegetable = vegetable;
	}
}
