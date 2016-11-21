package com.example.ep.controller;

import com.example.core.domain.User;
import com.example.core.service.LongRunningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by mac on 11/28/15.
 */
@RestController
@Slf4j
public class SyncAsyncEndPoint {

    @Autowired
    LongRunningService longRunningService;

    @RequestMapping(path = "/sync-call")
    public String sync() throws InterruptedException {
        Long time = longRunningService.bigTask();
        return time.toString();
    }

    @RequestMapping(path = "/async-call")
    public Long async() throws InterruptedException, ExecutionException {
        Future<Long> future = longRunningService.bigTaskAsync();
        return future.get();
    }


//    @RequestMapping(path = "/async-github")
//    public User asyncGitHubUser() throws InterruptedException, ExecutionException {
//        //Future<User> future = longRunningService.findGithubUser("mizanRahman");
//        return future.get();
//    }


    @RequestMapping("/long-polling")
    public DeferredResult<User> quotes() throws InterruptedException, ExecutionException {

        final DeferredResult<User> result = new DeferredResult<>();
        longRunningService.findGithubUser("mizanRahman", result);
        result.onCompletion(() -> {
            log.info("deferred result completed, threadCount={}, currentThread={}",
                    Thread.activeCount(), Thread.currentThread());
        });

        log.info("returning deferred result, threadCount={}, currentThread={}",
                Thread.activeCount(), Thread.currentThread());
        return result;
    }

    @RequestMapping("/long-polling2")
    public CompletableFuture<User> quotes1() throws InterruptedException, ExecutionException {

        CompletableFuture<User> f = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("long work started, threadCount={}, currentThread={}",
                        Thread.activeCount(), Thread.currentThread());
                longRunningService.bigTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User u = new User();
            u.setBlog("dfs");
            return u;

        });


        log.info("returning from controller, threadCount={}, currentThread={}",
                Thread.activeCount(), Thread.currentThread());

        return f;
    }

    @RequestMapping("/long-polling3")
    public DeferredResult<User> quotes3() throws InterruptedException, ExecutionException {

        final DeferredResult<User> result = new DeferredResult<>();

        longRunningService
                .getObservableUser("mizanRahman")
                .subscribe(user -> result.setResult(user), e -> result.setErrorResult(e))
        ;

        log.info("returning from controller, threadCount={}, currentThread={}",
                Thread.activeCount(), Thread.currentThread());

        return result;
    }


}
