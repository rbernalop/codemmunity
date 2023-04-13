package org.rbernalop.apichallenge.challenge.domain.value_object;

import jakarta.persistence.Column;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChallengeDescription {
    @Column(columnDefinition = "TEXT")
    private String description;

    public ChallengeDescription(String description) {
        if(description == null || description.isBlank()) {
            throw new IllegalArgumentException("Challenge description cannot be empty");
        }
        this.description = description;
    }

    public String getValue() {
        return description;
    }
}
