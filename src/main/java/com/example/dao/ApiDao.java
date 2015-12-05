package com.example.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mac on 12/3/15.
 */
@Slf4j
@Component
public class ApiDao extends RestTemplate {


    public <T> T get(String url, Class<T> responseType) throws RestClientException {

        try {

            HttpEntity<T> response = super.getForEntity(url, responseType);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.warn("client error exception. status code: {}", ex.getStatusCode());
            throw ex;

        } catch (ResourceAccessException ex) {
            log.warn("client error exception. status code: {}", ex.getLocalizedMessage());
            throw ex;

        }
    }
}
