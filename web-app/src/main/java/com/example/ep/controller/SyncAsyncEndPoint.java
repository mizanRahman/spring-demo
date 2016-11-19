package com.example.ep.controller;

import com.example.core.domain.User;
import com.example.core.service.LongRunningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
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
    @ResponseBody
    public DeferredResult<String> quotes() throws InterruptedException, ExecutionException {

        final DeferredResult<String> result = new DeferredResult<String>(null, Collections.emptyList());

        result.onCompletion(new Runnable() {
            public void run() {
                log.debug("long polling task completed, {}", result.getResult());
            }
        });

        String user = longRunningService.findGithubUser("mizanRahman").get();
        if (user != null) {
            result.setResult(user);
        }

        return result;
    }

}
