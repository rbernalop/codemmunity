package org.rbernalop.apitest.test.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestName {
    private String testName;

    public TestName(String testName) {
        if(testName == null || testName.isBlank()) {
            throw new IllegalArgumentException("Test name cannot be empty");
        }
        this.testName = testName;
    }

    public String getValue() {
        return testName;
    }
}
