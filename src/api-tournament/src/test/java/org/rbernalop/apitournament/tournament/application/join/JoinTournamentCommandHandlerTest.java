package org.rbernalop.apitournament.tournament.application.join;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apitournament.shared.application.tournament.join.JoinTournamentCommand;
import org.rbernalop.apitournament.shared.application.tournament.join.JoinTournamentCommandMother;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.aggregate.TournamentMother;
import org.rbernalop.apitournament.tournament.domain.exception.CompetitorAlreadyInTournamentException;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JoinTournamentCommandHandlerTest extends UnitTestCase {
    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private JoinTournamentCommandHandler handler;

    @Test
    void should_join_user_to_tournament() {
        // GIVEN
        Tournament tournament = TournamentMother.random();
        assertNotNull(tournament.getId());
        JoinTournamentCommand command = JoinTournamentCommandMother.fromTournamentId(tournament.getId().getValue());

        when(tournamentRepository.findById(tournament.getId())).thenReturn(Optional.of(tournament));

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

        // THEN
        verify(tournamentRepository, times(1)).findById(any());
        verify(tournamentRepository, times(1)).save(any());
    }

    @Test
    void should_throw_CompetitorAlreadyInTournamentException_when_user_already_in_tournament() {
        // GIVEN
        Tournament tournament = TournamentMother.random();
        assertNotNull(tournament.getId());
        JoinTournamentCommand command = JoinTournamentCommandMother.fromTournamentIdAndUser(tournament.getId().getValue(), tournament.getCreatorUsername());

        when(tournamentRepository.findById(tournament.getId())).thenReturn(Optional.of(tournament));

        // WHEN
        assertThrows(CompetitorAlreadyInTournamentException.class, () -> handler.handle(command));

        // THEN
        verify(tournamentRepository, times(1)).findById(any());
        verify(tournamentRepository, never()).save(any());
    }
}