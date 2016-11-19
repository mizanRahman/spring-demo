package com.example.core.service;

import com.example.core.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by mac on 11/28/15.
 */
@Service
@Slf4j
public class LongRunningService {

    @Autowired
    RestTemplate restTemplate;

    public long bigTask() throws InterruptedException {
        func();
        log.info("bigtask complete");
        return System.currentTimeMillis();
    }

    @Async
    public Future<Long> bigTaskAsync() throws InterruptedException {
        func();

        log.info("bigtask complete");
        return new AsyncResult<Long>(
                System.currentTimeMillis()
        );
    }

    private void func() throws InterruptedException {
        List<Integer> times = Arrays.asList(100,300, 20, 200, 7, 700, 3, 1000, 45, 377, 500);
        times.forEach(t -> {
            log.info("task: sleeptime={}, activeThreads={}, currentThread={}",
                    t, Thread.activeCount(), Thread.currentThread().getId());
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted");
            }
        });

    }


    @Async
    public Future<String> findGithubUser(String user) throws InterruptedException {
        log.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        //User results = restTemplate.getForObject(url, User.class);
        String results = restTemplate.getForObject("https://httpbin.org/get", String.class );
        // Artificial delay of 1s for demonstration purposes
        func();
        return new AsyncResult<>(results);
    }

}
