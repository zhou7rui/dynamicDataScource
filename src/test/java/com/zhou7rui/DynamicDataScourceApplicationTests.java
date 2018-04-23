package com.zhou7rui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataScourceApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Resource(name = "dynamicDataSource")
	private DataSource dataSource;


	@Test
	public void testDataSource(){

		System.out.println(dataSource);
	}




}
