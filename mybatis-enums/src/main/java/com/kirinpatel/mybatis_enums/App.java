package com.kirinpatel.mybatis_enums;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class App {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Test> tests = session.selectList(
                    "com.kirinpatel.mybatis_enums.TestDAO.getTestsByVegetable", Vegetable.POTATO);
            for (Test test : tests) {
                System.out.println(test.getId() + " " + test.getVegetable());
            }
        }
    }
}
