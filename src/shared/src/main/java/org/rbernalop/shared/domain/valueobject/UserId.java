package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class UserId extends Identifier {

    private String id;

    public UserId(String value) {
        super(value);
        this.id = value;
    }

    @Override
    public String getValue() {
        return id;
    }
}
