package com.example.apiclient;

import org.springframework.web.client.RestTemplate;

/**
 * Created by mac on 12/10/15.
 */
public class GithubApiClient {


    private RestTemplate client;

    public GithubApiClient() {
        this.client = new RestTemplate();
    }

    public String getRepositories() {
        return "octopress";




    }
}
