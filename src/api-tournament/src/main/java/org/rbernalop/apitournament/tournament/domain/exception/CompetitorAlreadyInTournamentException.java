package org.rbernalop.apitournament.tournament.domain.exception;

public class CompetitorAlreadyInTournamentException extends RuntimeException {
    public CompetitorAlreadyInTournamentException(String message) {
        super(message);
    }
}
