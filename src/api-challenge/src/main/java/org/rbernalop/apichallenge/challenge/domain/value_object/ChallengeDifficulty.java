package org.rbernalop.apichallenge.challenge.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChallengeDifficulty {
    private long difficulty;

    public ChallengeDifficulty(long difficulty) {
        if(difficulty < 0 || difficulty > 5) {
            throw new IllegalArgumentException("Difficulty must be between 0 and 5");
        }
        this.difficulty = difficulty;
    }

    public long getValue() {
        return difficulty;
    }
}
