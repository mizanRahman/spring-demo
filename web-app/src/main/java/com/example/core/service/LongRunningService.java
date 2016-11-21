package com.example.core.service;

import com.example.core.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

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
                    t, Thread.activeCount(), Thread.currentThread());
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted");
            }
        });

    }


    @Async
    public Future<User> findGithubUser(String userName, DeferredResult<User> result) throws InterruptedException {
        log.info("Looking up " + userName);
        String url = String.format("https://api.github.com/users/%s", userName);
        User user = restTemplate.getForObject(url, User.class);

        result.setResult(user);

        // Artificial delay of 1s for demonstration purposes
        func();
        return new AsyncResult<>(user);
    }

    public Observable<User> getObservableUser(String userName) {

        return Observable.<User>create(subscriber -> {
            log.info("long runnning task started");

            log.info("Looking up " + userName);
            String url = String.format("https://api.github.com/users/%s", userName);
            User user = restTemplate.getForObject(url, User.class);

            subscriber.onNext(user);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());
    }


}
