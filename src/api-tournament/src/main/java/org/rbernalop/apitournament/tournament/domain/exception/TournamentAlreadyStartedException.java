package org.rbernalop.apitournament.tournament.domain.exception;

public class TournamentAlreadyStartedException extends RuntimeException {
    public TournamentAlreadyStartedException(String message) {
        super(message);
    }
}
