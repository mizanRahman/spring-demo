package com.example.unit;

import com.example.SpringDemoApplication;
import com.example.core.domain.Card;
import com.example.core.repository.CardRepository;
import com.example.core.service.CardService;
import com.example.core.service.CardServiceImpl;
import com.example.core.service.LongRunningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Equals;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * Created by mac on 11/29/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
public class LongServiceTests {

    @Autowired
    LongRunningService service;

    @Test
    public void nestedCallTest() {
        int in = 23;
        Integer result = service.nestedCall(in);
        assertThat(result, is(in+5));
        System.out.println("result is = " + result);
    }

}
