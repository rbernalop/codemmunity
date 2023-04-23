package org.rbernalop.apitest.shared.application.test.check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
public class CheckScriptTestsQuery implements Query {
    private String challengeId;
    private String scriptContent;
    private String languageName;
}
