package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mac on 11/29/15.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService = new CardServiceImpl();


    @Test
    public void saveTest() {
        when(cardRepository.save(Mockito.any(Card.class)))
                .thenAnswer(new Answer<Card>() {
                    @Override
                    public Card answer(InvocationOnMock invocation) throws Throwable {
                        Card card = invocation.getArgumentAt(0, Card.class);
                        card.setId(0L);
                        return card;
                    }
                });

        Card card = new Card();
        card.setBalance(212);
        card.setPan("374632786");

        Card savedCard = cardService.save(card);

        verify(cardRepository, times(1)).save(card);
        assertThat(savedCard.getId(), is(notNullValue()));
    }

}
