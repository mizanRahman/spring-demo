package com.example.aop.logging;

import com.example.core.Response;
import com.example.core.domain.Card;

/**
 * Created by mac on 1/4/17.
 */
public class ResponseHandler {
    private Response<Card> cardResponse;

    public Response handleResonse(Card card) {
        return Response.<Card>builder()
                .data(card)
                .notification(Response.Notification.builder()
                        .type(Response.NotificationType.BALANCE_UPDATE)
                        .build())
                .build();
    }
}
