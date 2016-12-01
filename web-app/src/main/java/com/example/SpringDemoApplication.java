package com.example;

import com.example.application.hmac.HmacAccessFilter;
import com.example.core.domain.Card;
import com.example.core.repository.CardRepository;
import com.example.ep.filter.PreControllerServiceHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.Date;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        System.out.println("running my application...");
        SpringApplication.run(SpringDemoApplication.class, args);
    }

//    @Bean(name = "preControllerServiceHandlerRegister")
//    FilterRegistrationBean preControllerServiceHandler() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new DelegatingFilterProxy());
//        bean.setName("preControllerServiceHandle");
//        bean.setOrder(2);
//        return bean;
//    }

    @Bean
    HmacAccessFilter hmacFilter() {
        return new HmacAccessFilter();
    }


    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();

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
