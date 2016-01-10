package com.example.unit;

import com.example.SpringDemoApplication;
import com.example.core.domain.Card;
import com.example.core.repository.CardRepository;
import com.example.core.service.CardService;
import com.example.core.service.CardServiceImpl;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * Created by mac on 11/29/15.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
public class CardServiceTests {

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

        Card card = Card.builder().balance(212).pan("374632786").build();

        Card savedCard = cardService.create(card);

        verify(cardRepository, times(1)).save(card);
        assertThat(savedCard.getId(), is(notNullValue()));
    }

}
