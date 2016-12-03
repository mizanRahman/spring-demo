package com.example.core.domain;

import lombok.Value;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mac on 12/3/16.
 */
@RepositoryRestResource
@Value
public class UserRegistration {
    private String id;
    private String email;
    private String phoneNumber;
}
