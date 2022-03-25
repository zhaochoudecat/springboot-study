package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@EnableAutoConfiguration
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws Exception{

        //查看一下默认的数据源: class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());

        //获得数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        // xxx Template ：springboot已经配置好模板bean,拿来即用
        //关闭
        connection.close();
    }

}
