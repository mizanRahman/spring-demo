package com.example.application.gateway.tms;

import com.example.application.gateway.tms.model.TmsUpdateAtcRequest;
import com.example.application.gateway.tms.model.TmsUpdateAtcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by mac on 12/3/16.
 */
public class TmsApiGatewayImpl implements TmsApiGateway {


    /**
     * test with gatling
     */


    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Autowired
    private Scheduler httpClientScheduler;

    @Override
    public Observable<ResponseEntity<TmsUpdateAtcResponse>> updateAtc(TmsUpdateAtcRequest request) {
        ListenableFuture<ResponseEntity<TmsUpdateAtcResponse>> exchange =
                asyncRestTemplate.exchange(
                        "https://httpbin.org/post",
                        HttpMethod.POST,
                        HttpEntity.EMPTY,
                        TmsUpdateAtcResponse.class);

        return Observable.from(exchange, httpClientScheduler);  // httpClientScheduler??? or executor??entScheduler);

    }
}
