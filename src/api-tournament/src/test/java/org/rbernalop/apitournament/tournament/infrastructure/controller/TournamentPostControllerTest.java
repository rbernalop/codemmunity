package org.rbernalop.apitournament.tournament.infrastructure.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallengeMother;
import org.rbernalop.apitournament.tournament.domain.port.RandomChallengeRetriever;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TournamentPostControllerTest extends IntegrationTestCase {
    private final String TOURNAMENT_CREATE_URL = "/api/v1/tournament";

    @MockBean
    private RandomChallengeRetriever randomChallengeRetriever;

    @Autowired
    private TournamentRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void should_create_a_tournament() {
        assertDoesNotThrow(() -> {
            // GIVEN
            TournamentPostRequest request = TournamentPostRequestMother.randomFromRounds(1);
            UserUsername creatorUserName = new UserUsername(Faker.instance().name().username());
            List<TournamentChallenge> challenges = List.of(TournamentChallengeMother.random());

            when(randomChallengeRetriever.getRandomChallenges(request.getRounds())).thenReturn(challenges);

            // WHEN
            assertRequestWithBody(
                HttpMethod.POST,
                TOURNAMENT_CREATE_URL + "?user=" + creatorUserName,
                request,
                HttpStatus.CREATED
            );

            // THEN
            verify(randomChallengeRetriever, times(1)).getRandomChallenges(request.getRounds());
            assertEquals(1, repository.count());
        });
    }
}