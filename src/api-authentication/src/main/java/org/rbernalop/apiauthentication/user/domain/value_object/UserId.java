package org.rbernalop.apiauthentication.user.domain.value_object;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

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
