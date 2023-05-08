package org.rbernalop.apitournament.shared.application.tournament.find.by_id;

import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;

public class FindTournamentByIdQueryMother {
    public static FindTournamentByIdQuery fromId(TournamentId id) {
        return new FindTournamentByIdQuery(id.getValue());
    }
}