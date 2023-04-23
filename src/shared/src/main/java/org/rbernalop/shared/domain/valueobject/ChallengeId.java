package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ChallengeId extends Identifier {
    private String challengeId;

    public ChallengeId(String value) {
        super(value);
        this.challengeId = value;
    }

    @Override
    public String getValue() {
        return challengeId;
    }
}
