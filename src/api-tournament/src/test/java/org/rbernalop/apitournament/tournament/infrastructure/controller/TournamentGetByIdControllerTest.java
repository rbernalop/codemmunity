package org.rbernalop.apitournament.tournament.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.aggregate.TournamentMother;
import org.rbernalop.apitournament.tournament.domain.port.TournamentChallengeCategoryRepository;
import org.rbernalop.apitournament.tournament.domain.port.TournamentChallengeRepository;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TournamentGetByIdControllerTest extends IntegrationTestCase {
    private static final String TOURNAMENT_GET_BY_ID_URL = "/api/v1/tournament/{id}";

    @Autowired
    private TournamentChallengeCategoryRepository tournamentChallengeCategoryRepository;

    @Autowired
    private TournamentChallengeRepository tournamentChallengeRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @AfterEach
    void tearDown() {
        tournamentChallengeRepository.deleteAll();
        tournamentChallengeCategoryRepository.deleteAll();
        tournamentRepository.deleteAll();
    }

    @Test
    void should_get_tournament_by_id() throws Exception {
        // GIVEN
        Tournament tournament = TournamentMother.random();
        tournamentRepository.save(tournament);

        // WHEN
        assertNotNull(tournament.getId());
        MvcResult result = assertRequest(
            HttpMethod.GET,
            TOURNAMENT_GET_BY_ID_URL.replace("{id}", tournament.getId().getValue()),
            HttpStatus.OK
        );

        // THEN
        TournamentGetByIdResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), TournamentGetByIdResponse.class);
        assertEquals(tournament.getId().getValue(), response.getId());
        assertEquals(tournament.getName(), response.getName());
        assertEquals(tournament.getDescription(), response.getDescription());
        assertEquals(tournament.getBeginningDate(), response.getBeginningDate());
        assertEquals(tournament.getSize(), response.getSize());
        assertEquals(tournament.getCompetitors().size(), response.getLeaderboard().size());
    }
}