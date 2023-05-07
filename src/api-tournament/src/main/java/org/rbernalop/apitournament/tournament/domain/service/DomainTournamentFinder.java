package org.rbernalop.apitournament.tournament.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;

@AllArgsConstructor
public class DomainTournamentFinder {
    private final TournamentRepository tournamentRepository;

    public Tournament find(TournamentId id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament with id " + id + " not found"));
    }
}
