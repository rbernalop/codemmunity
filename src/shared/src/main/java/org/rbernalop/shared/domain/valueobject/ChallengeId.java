package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ChallengeId extends Identifier {
    private String id;

    public ChallengeId(String value) {
        super(value);
        this.id = value;
    }

    @Override
    public String getValue() {
        return id;
    }
}
