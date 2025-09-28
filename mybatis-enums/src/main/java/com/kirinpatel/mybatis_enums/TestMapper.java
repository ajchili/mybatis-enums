package com.kirinpatel.mybatis_enums;

import org.apache.ibatis.annotations.Param;

public interface TestMapper {

	public Test getTest(@Param("id") String id);

	public Test getTestsByVegetable(@Param("vegetable") Vegetable vegetable);
}
