package org.rbernalop.servicesynchronization.shared.application.script.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindScriptByIdQuery implements Query {
    private String id;
}
