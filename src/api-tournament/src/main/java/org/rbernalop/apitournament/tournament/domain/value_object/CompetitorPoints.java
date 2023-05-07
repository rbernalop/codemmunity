package org.rbernalop.apitournament.tournament.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.exception.NegativeException;

@NoArgsConstructor
public class CompetitorPoints {
    private Integer points;

    public CompetitorPoints(Integer points) {
        if (points < 0) throw new NegativeException("Points can't be negative");
        this.points = points;
    }

    public Integer getValue() {
        return points;
    }
}
