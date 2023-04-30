package org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindChallengeScriptByIdQuery implements Query {
    private String challengeId;
    private String username;

    public static FindChallengeScriptByIdQuery fromChallengeScriptId(ChallengeScriptId id) {
        return new FindChallengeScriptByIdQuery(id.getChallengeId(), id.getUserUsername());
    }
}
