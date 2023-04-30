package org.rbernalop.servicechallengesynchronization.shared.application.user_script.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JoinUserScriptCommand implements Command {
    private String username;
    private String scriptId;
}
