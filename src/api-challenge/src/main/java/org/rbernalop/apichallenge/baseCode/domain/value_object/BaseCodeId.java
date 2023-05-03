package org.rbernalop.apichallenge.baseCode.domain.value_object;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class BaseCodeId implements Serializable {
    @Embedded
    private ChallengeId challengeId;

    @Embedded
    private LanguageName languageName;

    public BaseCodeId(ChallengeId challengeId, LanguageName languageName) {
        this.challengeId = challengeId;
        this.languageName = languageName;
    }

    public String getChallengeId() {
        return challengeId.getValue();
    }

    public String getLanguageName() {
        return languageName.getValue();
    }
}
