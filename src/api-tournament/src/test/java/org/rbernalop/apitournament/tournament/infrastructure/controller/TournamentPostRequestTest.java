package org.rbernalop.apitournament.tournament.infrastructure.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TournamentPostRequestTest extends IntegrationTestCase {
    private final String TOURNAMENT_CREATE_URL = "/api/v1/tournament";

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
            TournamentPostRequest request = TournamentPostRequestMother.random();
            UserUsername creatorUserName = new UserUsername(Faker.instance().name().username());

            // WHEN
            assertRequestWithBody(
                HttpMethod.POST,
                TOURNAMENT_CREATE_URL + "?user=" + creatorUserName,
                request,
                HttpStatus.CREATED
            );

            // THEN
            assertEquals(1, repository.count());
        });
    }
}