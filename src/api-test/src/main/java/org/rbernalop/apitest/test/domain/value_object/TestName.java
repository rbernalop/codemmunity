package org.rbernalop.apitest.test.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestName {
    private String name;

    public TestName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Test name cannot be empty");
        }
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
