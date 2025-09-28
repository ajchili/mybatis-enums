package com.kirinpatel.mybatis_enums;

import org.apache.ibatis.annotations.Param;

public interface TestDAO {

	public Test getTest(@Param("id") String id);

	public Test getTestsByVegetable(@Param("vegetable") Vegetable vegetable);
}
