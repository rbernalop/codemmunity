package org.rbernalop.servicesynchronization.shared.application.script.find;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindScriptByIdResponse implements Response {
    private String content;
    private List<String> onlineUsers;
}
