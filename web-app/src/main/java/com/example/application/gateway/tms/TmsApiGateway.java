package com.example.application.gateway.tms;

import com.example.application.gateway.tms.model.TmsUpdateAtcRequest;
import com.example.application.gateway.tms.model.TmsUpdateAtcResponse;
import org.springframework.http.ResponseEntity;
import rx.Observable;

/**
 * Created by mac on 12/3/16.
 */
public interface TmsApiGateway {
    Observable<ResponseEntity<TmsUpdateAtcResponse>> updateAtc(TmsUpdateAtcRequest request);
}
