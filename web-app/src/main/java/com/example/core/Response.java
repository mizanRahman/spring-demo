package com.example.core;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mac on 1/4/17.
 */
@Data
@Builder
public class Response<T> {

    T data;
    Notification notification;

    @Builder
    @Data
    public static class Notification {
        private NotificationType type;
        private String message;


    }

    public enum NotificationType {
        EMERGENCY(1),
        BALANCE_UPDATE(2);

        private Integer code;

        NotificationType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public static NotificationType getValue(int code) {
            for (NotificationType e :
                    NotificationType.values()) {
                if (e.code == code) return e;
            }
            return null;
        }
    }

}
