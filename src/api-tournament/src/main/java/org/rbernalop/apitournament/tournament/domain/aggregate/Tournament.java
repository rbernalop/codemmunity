package org.rbernalop.apitournament.tournament.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.entity.Competitor;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.apitournament.tournament.domain.exception.CompetitorAlreadyInTournamentException;
import org.rbernalop.apitournament.tournament.domain.exception.FullTournamentException;
import org.rbernalop.apitournament.tournament.domain.exception.TournamentAlreadyStartedException;
import org.rbernalop.apitournament.tournament.domain.value_object.*;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Embedded
    private TournamentSize size;

    @Embedded
    private TournamentRounds rounds;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.tournament", fetch = FetchType.EAGER)
    private List<Competitor> competitors;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "tournament", fetch = FetchType.EAGER)
    private List<TournamentChallenge> challenges;

    public static Tournament create(TournamentId id, TournamentName name, TournamentDescription description,
            UserUsername creatorUsername, TournamentBeginningDate beginningDate, TournamentSize size,
            List<TournamentChallenge> challenges, TournamentRounds rounds) {
        Tournament tournament = new Tournament();
        tournament.id = id;
        tournament.name = name;
        tournament.description = description;
        tournament.creatorUsername = creatorUsername;
        tournament.beginningDate = beginningDate;
        tournament.size = size;
        tournament.competitors = new ArrayList<>(List.of(Competitor.create(creatorUsername, tournament)));
        tournament.challenges = challenges;
        tournament.rounds = rounds;
        challenges.forEach(challenge -> challenge.setTournament(tournament));
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

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public int getSize() {
        return size.getValue();
    }

    public int getRounds() {
        return rounds.getValue();
    }

    public List<TournamentChallenge> getChallenges() {
        return challenges;
    }

    public void join(UserUsername user) {
        Competitor competitor = Competitor.create(user, this);
        if (beginningDate.getValue().before(Date.from(Instant.now())))
            throw new TournamentAlreadyStartedException("Tournament already started");
        if(competitors.contains(competitor))
            throw new CompetitorAlreadyInTournamentException("User already joined");
        if (size.getValue() <= competitors.size())
            throw new FullTournamentException("Tournament is full");
        competitors.add(competitor);
    }

    public TournamentChallenge getCurrentChallenge() {
        return challenges.get(0); // TODO: Add actual round
    }
}
