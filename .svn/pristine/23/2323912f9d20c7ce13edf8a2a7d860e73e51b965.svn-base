package com.wzlue.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.wzlue.WzlueApplication;
import com.wzlue.common.utils.RedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= WzlueApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class TestRedis {

   @Test
    public void getTest(){
       System.out.println(RedisUtils.get("dd"));
   }
}
