package com.example;

import com.example.config.RootConfig;
import com.example.core.domain.Card;
import com.example.core.repository.CardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAsync
//@ComponentScan(basePackages = {"com.example.core", "lib.demo.core"})
public class SpringDemoApplication {

    public static void main(String[] args) {
        System.out.println("running my application...");
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Bean
    public ObjectMapper mapper() {
        System.out.println("object mapper bean is created");
        return new ObjectMapper();

    }

    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }
}

@Component
class InitialRunner implements CommandLineRunner {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void run(String... args) throws Exception {
        cardRepository.save(Card.builder().balance(10).pan("3333").expiryDate(new Date()).build());
        cardRepository.save(Card.builder().balance(20).pan("3334").expiryDate(new Date()).build());
        cardRepository.save(Card.builder().balance(30).pan("3335").expiryDate(new Date()).build());
        cardRepository.save(Card.builder().balance(40).pan("3336").expiryDate(new Date()).build());
        cardRepository.save(Card.builder().balance(50).pan("3337").expiryDate(new Date()).build());
    }
}
