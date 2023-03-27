package org.rbernalop.apiexecution.language.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiexecution.language.domain.value_object.LanguageCompileCommand;
import org.rbernalop.apiexecution.language.domain.value_object.LanguageName;
import org.rbernalop.apiexecution.language.domain.value_object.LanguageRunCommand;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.Identifier;
import org.rbernalop.shared.domain.valueobject.LanguageId;

@Entity
@Table(name = "languages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Language extends AggregateRoot {
    @EmbeddedId
    private LanguageId id;

    @Embedded
    private LanguageName name;

    @Embedded
    private LanguageCompileCommand compileCommand;

    @Embedded
    private LanguageRunCommand runCommand;

    public Language(LanguageId id, LanguageName name, LanguageCompileCommand compileCommand, LanguageRunCommand runCommand) {
        this.id = id;
        this.name = name;
        this.compileCommand = compileCommand;
        this.runCommand = runCommand;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }

    public String getCompileCommand() {
        return compileCommand.getValue();
    }

    public String getRunCommand() {
        return runCommand.getValue();
    }
}
