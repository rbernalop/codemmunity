package org.rbernalop.apitournament.tournament.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentBeginningDate;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentDescription;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentName;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.Date;

@Entity
@Table(name = "tournaments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Tournament extends AggregateRoot {
    @EmbeddedId
    private TournamentId id;

    @Embedded
    private TournamentName name;

    @Embedded
    private TournamentDescription description;

    @Embedded
    private UserUsername creatorUsername;

    @Embedded
    private TournamentBeginningDate beginningDate;

    public static Tournament create(TournamentId id, TournamentName name, TournamentDescription description,
            UserUsername creatorUsername, TournamentBeginningDate beginningDate) {
        Tournament tournament = new Tournament();
        tournament.id = id;
        tournament.name = name;
        tournament.description = description;
        tournament.creatorUsername = creatorUsername;
        tournament.beginningDate = beginningDate;
        return tournament;
    }

    @Override
    public TournamentId getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public String getCreatorUsername() {
        return creatorUsername.getValue();
    }

    public Date getBeginningDate() {
        return beginningDate.getValue();
    }
}
