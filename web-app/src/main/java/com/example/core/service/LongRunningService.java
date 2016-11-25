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
        List<Integer> times = Arrays.asList(100, 300, 20, 200, 7, 700, 3, 1000, 45, 377, 500);
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
            log.info("long running task started");

            log.info("Looking up " + userName);
            String url = String.format("https://api.github.com/users/%s", userName);
            User user = restTemplate.getForObject(url, User.class);

            subscriber.onNext(user);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());
    }

    public Integer nestedCall(Integer i) {
        return f1(i).toBlocking().single();
    }


    private Observable<Integer> f1(Integer p1) {
        return f21("f1 started"+p1)
                .flatMap(aVoid -> f2(p1+1))
                .flatMap(r2 -> f22(r2, "f1  finished"));
    }

    private Observable<Integer> f2(Integer p2) {
        return f21("f2 start"+p2)
                .flatMap(aVoid -> f3(p2 + 1))
                .flatMap(r3 -> f22(r3, "f2 end"));
    }

    private Observable<Void> f21(String msg) {
        log.info(msg);
        return Observable.just(null);
    }

    private Observable<Integer> f22(Integer r3, String msg) {
        Integer r2 = r3 + 1;
        log.info(msg);
        return Observable.just(r2);
    }

    private Observable<Integer> f3(Integer p3) {
        log.info("f3 start"+p3);
        Integer r3 = p3 + 1;
        log.info("f3 end");
        return Observable.just(r3);
    }


}
