package org.rbernalop.apiexecution.language.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.LanguageName;
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

    public static Language create(LanguageId id, LanguageName name) {
        Language language = new Language();
        language.id = id;
        language.name = name;
        return language;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }
}
