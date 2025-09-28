package com.kirinpatel.mybatis_enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class VegetableTypeHandler extends BaseTypeHandler<Vegetable> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Vegetable parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setObject(i, parameter.getValue());
	}

	@Override
	public Vegetable getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		return getVegetableByValue(value);
	}

	@Override
	public Vegetable getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		return getVegetableByValue(value);
	}

	@Override
	public Vegetable getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		return getVegetableByValue(value);
	}

	private Vegetable getVegetableByValue(String value) {
		if (value == null) {
			return null;
		}
		for (Vegetable vegetable : Vegetable.values()) {
			if (vegetable.getValue().equals(value)) {
				return vegetable;
			}
		}
		throw new IllegalArgumentException("Unknown vegetable value: " + value);
	}
}
