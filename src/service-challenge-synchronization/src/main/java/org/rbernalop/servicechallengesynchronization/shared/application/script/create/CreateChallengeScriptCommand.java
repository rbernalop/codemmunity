package org.rbernalop.servicechallengesynchronization.shared.application.script.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateChallengeScriptCommand implements Command {
    private String challengeId;
    private String username;
    private String content;
    private String languageName;
}
