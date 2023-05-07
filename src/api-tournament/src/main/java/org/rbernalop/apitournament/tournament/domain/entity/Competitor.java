package org.rbernalop.apitournament.tournament.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.value_object.CompetitorId;
import org.rbernalop.apitournament.tournament.domain.value_object.CompetitorPoints;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Entity
@Table(name = "competitors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Competitor {
    @EmbeddedId
    private CompetitorId id;

    @Embedded
    @EqualsAndHashCode.Exclude
    private CompetitorPoints points;


    public static Competitor create(UserUsername competitorUsername, Tournament tournament) {
        Competitor competitor = new Competitor();
        competitor.id = new CompetitorId(competitorUsername, tournament);
        competitor.points = new CompetitorPoints(0);
        return competitor;
    }

    public CompetitorId getId() {
        return id;
    }

    public String getCompetitorUsername() {
        return id.getCopetitorUsername();
    }

    public Tournament getTournament() {
        return id.getTournament();
    }
}
