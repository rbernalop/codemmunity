package org.rbernalop.apiexecution.language.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiexecution.language.domain.exception.InvalidLanguageDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class LanguageRunCommand {
    private String runCommand;

    public LanguageRunCommand(String runCommand) {
        if (runCommand == null || runCommand.isBlank()) {
            throw new InvalidLanguageDataException("Run command cannot be empty");
        }
        this.runCommand = runCommand;
    }

    public String getValue() {
        return runCommand;
    }
}
