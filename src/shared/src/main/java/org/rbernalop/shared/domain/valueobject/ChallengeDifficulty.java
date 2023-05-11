package org.rbernalop.shared.domain.valueobject;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChallengeDifficulty {
    private long difficulty;

    public ChallengeDifficulty(long difficulty) {
        if(difficulty < 1 || difficulty > 5) {
            throw new IllegalArgumentException("Difficulty must be between 1 and 5");
        }
        this.difficulty = difficulty;
    }

    public long getValue() {
        return difficulty;
    }
}
