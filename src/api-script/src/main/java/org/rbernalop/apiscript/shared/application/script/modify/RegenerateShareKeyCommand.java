package org.rbernalop.apiscript.shared.application.script.modify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegenerateShareKeyCommand implements Command {
    private String id;
    private String shareKey;
}
