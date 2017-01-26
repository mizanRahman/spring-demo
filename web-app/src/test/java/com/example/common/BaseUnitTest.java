package com.example.common;

import com.example.SpringDemoApplication;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by mac on 1/18/17.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
public abstract class BaseUnitTest {

}
