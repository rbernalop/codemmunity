package org.rbernalop.apiexecution.language.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiexecution.language.domain.exception.InvalidLanguageDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class LanguageCompileCommand {
    private String compileCommand;

    public LanguageCompileCommand(String compileCommand) {
        if (compileCommand == null || compileCommand.isBlank()) {
            throw new InvalidLanguageDataException("Compile command cannot be empty");
        }
        this.compileCommand = compileCommand;
    }

    public String getValue() {
        return compileCommand;
    }
}
