package org.rbernalop.servicechallengesynchronization.shared.application.user_script.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindUsersByScriptIdQuery implements Query {
    private String scriptId;
}
