package com.zescs.dossier.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class })
public class AppTest {
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Test
	public void testOne(){
		BoundHashOperations<String, String, String> bs = redisTemplate.boundHashOps("menu");
//		bs.put("1","第一条数据");
		System.out.println(bs.get("1"));
	}
}
