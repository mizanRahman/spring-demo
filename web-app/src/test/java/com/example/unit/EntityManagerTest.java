package com.example.unit;

import com.example.SpringDemoApplication;
import com.example.core.domain.Card;
import com.example.core.repository.CardRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by mac on 11/30/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = SpringDemoApplication.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/dbunit/cardData.xml")
public class EntityManagerTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @ExpectedDatabase(value = "/dbunit/cardDataAfterAdd.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Transactional
    public void savetest() {
        Card card = Card.builder()
                .balance(90)
                .pan("3352732138298")
                .build();
        entityManager.persist(card);


    }


}
