package org.rbernalop.apichallenge.challenge.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryName {
    private String name;

    public CategoryName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
