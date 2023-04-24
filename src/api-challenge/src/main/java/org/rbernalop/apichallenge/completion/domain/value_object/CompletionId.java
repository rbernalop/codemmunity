package org.rbernalop.apichallenge.completion.domain.value_object;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class CompletionId implements Serializable {
    @Embedded
    private ChallengeId challengeId;

    @Embedded
    private UserUsername userUsername;

    public CompletionId(ChallengeId challengeId, UserUsername userUsername) {
        this.challengeId = challengeId;
        this.userUsername = userUsername;
    }

    public String getChallengeId() {
        return challengeId.getValue();
    }

    public String getUserUsername() {
        return userUsername.getValue();
    }
}
