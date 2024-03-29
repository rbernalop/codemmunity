package org.rbernalop.servicesynchronization.shared.application.user_script.delete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LeaveUserScriptCommand implements Command {
    private String username;
    private String scriptId;
}
