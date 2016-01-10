package com.example.integration;

import com.example.SpringDemoApplication;
import com.example.config.ConnectionSettings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by mac on 1/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringDemoApplication.class)
@WebIntegrationTest
public class SettingsIntegrationTests {


    @Autowired
    private ConnectionSettings connectionSettings;

    @Test
    public void configLoadedCorrectly() {
        String url = connectionSettings.getDev().getUrl();
        System.out.println("url=" + url);
//        assertThat(url, is("http://dev.bar.com"));
    }


}
