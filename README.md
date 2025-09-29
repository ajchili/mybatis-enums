# mybatis-enums

Playground for learning how to properly use enums within a Postgres database via MyBatis.

## What is the issue?

Java enums fail to be cast to their "value", the enum name is used instead. For example `Vegetable.POTATO` incorrectly maps to `"POTATO"` instead of `"Potato"`.

```bash
### The error occurred while setting parameters
### SQL: SELECT id, enum     FROM test_schema.test_table     WHERE enum = ?::test_schema.test_enum
### Cause: org.postgresql.util.PSQLException: ERROR: invalid input value for enum test_schema.test_enum: "POTATO"
        at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:30)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:156)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:147)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:142)
        at com.kirinpatel.mybatis_enums.App.main(App.java:19)
Caused by: org.postgresql.util.PSQLException: ERROR: invalid input value for enum test_schema.test_enum: "POTATO"
        at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2736)
        at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2421)
        at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:372)
        at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:525)
        at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:435)
        at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:196)
        at org.postgresql.jdbc.PgPreparedStatement.execute(PgPreparedStatement.java:182)
        at org.apache.ibatis.executor.statement.PreparedStatementHandler.query(PreparedStatementHandler.java:65)
        at org.apache.ibatis.executor.statement.RoutingStatementHandler.query(RoutingStatementHandler.java:80)
        at org.apache.ibatis.executor.SimpleExecutor.doQuery(SimpleExecutor.java:65)
        at org.apache.ibatis.executor.BaseExecutor.queryFromDatabase(BaseExecutor.java:336)
        at org.apache.ibatis.executor.BaseExecutor.query(BaseExecutor.java:158)
        at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:110)
        at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:90)
        at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:154)
```

## How can we fix it?

1. Add a type handler (e.g. [VegetableTypeHandler](./mybatis-enums/src/main/java/com/kirinpatel/mybatis_enums/VegetableTypeHandler.java))
2. Use type handler within MyBatis configuration
   ```xml
   <typeHandlers>
    <typeHandler handler="com.kirinpatel.mybatis_enums.VegetableTypeHandler" javaType="com.kirinpatel.mybatis_enums.Vegetable"/>
   </typeHandlers>
   ```
   OR
   ```xml
   <select id="getTestsByVegetable" parameterType="com.kirinpatel.mybatis_enums.Vegetable" resultMap="TestResultMap">
     SELECT id, enum
     FROM test_schema.test_table
     WHERE enum = #{vegetable, typeHandler=com.kirinpatel.mybatis_enums.VegetableTypeHandler}::test_schema.test_enum
   </select>
   ``
   ```
3. Mark the type within the result map
   ```xml
   <result property="vegetable" column="enum" javaType="com.kirinpatel.mybatis_enums.Vegetables" />
   ```
