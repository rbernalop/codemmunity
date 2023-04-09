package org.rbernalop.servicesynchronization.shared.application.user_script.find;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindUsersByScriptIdQuery implements Query {
    private String scriptId;
}
