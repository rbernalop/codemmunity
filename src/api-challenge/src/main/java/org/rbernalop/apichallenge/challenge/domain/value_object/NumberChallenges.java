package org.rbernalop.apichallenge.challenge.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberChallenges {
    private int numberChallenges;

    public NumberChallenges(int numberChallenges) {
        if(numberChallenges <= 0) {
            throw new IllegalArgumentException("Number of challenges must be greater than 0");
        }
        this.numberChallenges = numberChallenges;
    }

    public int getValue() {
        return numberChallenges;
    }
}
