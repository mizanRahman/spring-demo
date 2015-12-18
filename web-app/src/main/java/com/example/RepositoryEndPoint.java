package com.example;

import com.example.apiclient.GithubApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mac on 12/10/15.
 */

@RestController
@RequestMapping("/repositories")
@Slf4j
public class RepositoryEndPoint {


    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        GithubApiClient client = new GithubApiClient();
        log.debug("repositories: {}",client.getRepositories());
        return client.getRepositories();
    }
}
