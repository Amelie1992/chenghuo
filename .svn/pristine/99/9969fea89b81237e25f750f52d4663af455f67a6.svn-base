package com.wzlue.web.test;

import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.wzlue.WzlueApplication;
import com.wzlue.web.common.config.AsyncTask;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= WzlueApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SpringbootAsyncApplicationTests {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            asyncTask.doTask(i);
        }
        long end = System.currentTimeMillis();
        logger.info("运行时间"+(end-start));
    }
}
