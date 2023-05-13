package org.rbernalop.apitournament.tournament.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentChallengeBaseCodeId;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

@Entity
@Table(name = "tournament_challenge_base_codes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class TournamentChallengeBaseCode {
    @EmbeddedId
    private TournamentChallengeBaseCodeId id;

    @Embedded
    private ScriptContent content;

    public static TournamentChallengeBaseCode create(TournamentChallengeBaseCodeId tournamentChallengeBaseCodeId, ScriptContent content) {
        TournamentChallengeBaseCode baseCode = new TournamentChallengeBaseCode();
        baseCode.id = tournamentChallengeBaseCodeId;
        baseCode.content = content;
        return baseCode;
    }

    public String getChallengeId() {
        return id.getChallenge().getId().getValue();
    }

    public String getLanguageName() {
        return id.getLanguageName();
    }

    public String getContent() {
        return content.getValue();
    }
}
