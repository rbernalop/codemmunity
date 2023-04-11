package org.rbernalop.apichallenge.challenge.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChallengeTitle {
    private String title;

    public ChallengeTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Challenge title cannot be empty");
        }
        this.title = title;
    }

    public String getValue() {
        return title;
    }
}
