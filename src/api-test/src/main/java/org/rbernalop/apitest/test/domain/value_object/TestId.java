package org.rbernalop.apitest.test.domain.value_object;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class TestId extends Identifier {
    private String testId;

    public TestId(String testId) {
        super(testId);
        this.testId = testId;
    }

    @Override
    public String getValue() {
        return testId;
    }
}
