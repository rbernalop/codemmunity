package org.rbernalop.apitournament.tournament.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.aggregate.TournamentMother;
import org.rbernalop.apitournament.tournament.domain.port.TournamentChallengeCategoryRepository;
import org.rbernalop.apitournament.tournament.domain.port.TournamentChallengeRepository;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.UserUsernameMother;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TournamentPostJoinControllerTest extends IntegrationTestCase {
    private static final String URL = "/api/v1/tournament/{id}/join";

    @Autowired
    private TournamentChallengeCategoryRepository tournamentChallengeCategoryRepository;

    @Autowired
    private TournamentChallengeRepository tournamentChallengeRepository;


    @Autowired
    private TournamentRepository repository;

    @AfterEach
    void tearDown() {
        tournamentChallengeRepository.deleteAll();
        tournamentChallengeCategoryRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    void should_join_tournament() throws Exception {
        // GIVEN
        Tournament tournament = TournamentMother.random();
        repository.save(tournament);
        UserUsername userToJoin = UserUsernameMother.random();

        // WHEN
        assertNotNull(tournament.getId());
        assertRequest(
            HttpMethod.POST,
            URL.replace("{id}", tournament.getId().getValue()) + "?user=" + userToJoin.getValue(),
            HttpStatus.OK
        );

        // THEN
        tournament = repository.findById(tournament.getId()).orElseThrow();
        assertEquals(2, tournament.getCompetitors().size());
    }
}