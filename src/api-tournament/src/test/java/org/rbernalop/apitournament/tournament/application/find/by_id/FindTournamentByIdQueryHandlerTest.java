package org.rbernalop.apitournament.tournament.application.find.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdQuery;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdQueryMother;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdResponse;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.aggregate.TournamentMother;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindTournamentByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private FindTournamentByIdQueryHandler findTournamentByIdQueryHandler;

    @Test
    void should_find_tournament_by_id() {
        // GIVEN
        Tournament tournament = TournamentMother.random();
        FindTournamentByIdQuery findTournamentByIdQuery = FindTournamentByIdQueryMother.fromId(tournament.getId());

        when(tournamentRepository.findById(tournament.getId())).thenReturn(Optional.of(tournament));

        // WHEN
        FindTournamentByIdResponse findTournamentByIdResponse =
                assertDoesNotThrow(() -> findTournamentByIdQueryHandler.handle(findTournamentByIdQuery));

        // THEN
        verify(tournamentRepository, times(1)).findById(tournament.getId());
        assertEquals(tournament.getId().getValue(), findTournamentByIdResponse.getId());
        assertEquals(tournament.getName(), findTournamentByIdResponse.getName());
        assertEquals(tournament.getDescription(), findTournamentByIdResponse.getDescription());
        assertEquals(tournament.getBeginningDate(), findTournamentByIdResponse.getBeginningDate());
        assertEquals(tournament.getCompetitors().size(), findTournamentByIdResponse.getCompetitors().size());
        assertEquals(tournament.getSize(), findTournamentByIdResponse.getSize());

    }
}