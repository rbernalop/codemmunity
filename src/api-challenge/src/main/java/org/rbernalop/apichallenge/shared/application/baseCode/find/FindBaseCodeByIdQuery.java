package org.rbernalop.apichallenge.shared.application.baseCode.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindBaseCodeByIdQuery implements Query {
    private String challengeId;
    private String languageName;
}
