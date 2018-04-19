package com.zhou7rui.dao;

import com.zhou7rui.config.DataSourceContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectByExample() throws Exception {

        Map<String,Object> configMap = new HashMap<>();

        configMap.put(DataSourceContextHolder.DATASOURCE_KEY,"test");
        configMap.put(DataSourceContextHolder.DATASOURCE_DRIVER,"com.mysql.jdbc.Driver");
        configMap.put(DataSourceContextHolder.DATASOURCE_URL,"jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        configMap.put(DataSourceContextHolder.DATASOURCE_USERNAME,"root");
        configMap.put(DataSourceContextHolder.DATASOURCE_PASSWORD,"123");

        DataSourceContextHolder.setDataSource(configMap);

        userMapper.selectAll().forEach(System.out::println);


    }

}