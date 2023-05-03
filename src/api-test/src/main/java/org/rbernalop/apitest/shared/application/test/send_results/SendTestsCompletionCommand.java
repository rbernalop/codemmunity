package org.rbernalop.apitest.shared.application.test.send_results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.command.Command;

@AllArgsConstructor
@Getter
@Setter
public class SendTestsCompletionCommand implements Command {
    private boolean passed;
    private String username;
    private String challengeId;
    private String scriptContent;
    private String languageName;
}
