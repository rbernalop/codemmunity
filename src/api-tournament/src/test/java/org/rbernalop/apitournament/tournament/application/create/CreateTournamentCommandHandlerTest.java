package org.rbernalop.apitournament.tournament.application.create;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apitournament.shared.application.tournament.create.CreateTournamentCommand;
import org.rbernalop.apitournament.shared.application.tournament.create.CreateTournamentCommandMother;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTournamentCommandHandlerTest extends UnitTestCase {
    @Mock
    private TournamentRepository repository;

    @InjectMocks
    private CreateTournamentCommandHandler handler;

    @Test
    void should_create_tournament() {
        // GIVEN
        CreateTournamentCommand command = CreateTournamentCommandMother.random();

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

        // THEN
        verify(repository, times(1)).save(any());
    }

    @ParameterizedTest
    @MethodSource("invalidCommands")
    void should_throw_InvalidTournamentDataException_when_command_is_invalid(CreateTournamentCommand command) {
        // WHEN
        assertThrows(InvalidTournamentDataException.class, () -> handler.handle(command));

        // THEN
        verify(repository, never()).save(any());
    }

    private static Object[] invalidCommands() {
        return new Object[]{
                CreateTournamentCommandMother.randomWithInvalidName(),
                CreateTournamentCommandMother.randomWithInvalidDescription(),
                CreateTournamentCommandMother.randomWithInvalidDate(),
                CreateTournamentCommandMother.randomWithInvalidSize()
        };
    }
}