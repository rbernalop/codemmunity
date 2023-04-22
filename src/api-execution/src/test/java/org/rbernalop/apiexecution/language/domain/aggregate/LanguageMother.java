package org.rbernalop.apiexecution.language.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.LanguageId;

import java.util.List;

public class LanguageMother {
    private final static Faker faker = new Faker();

    public static Language random() {
        return Language.create(
            new LanguageId(faker.internet().uuid()),
            new LanguageName(faker.lorem().word())
        );
    }

    public static List<Language> randomList() {
        return List.of(random());
    }

    public static Language mockLanguage() {
        return Language.create(
            new LanguageId(faker.internet().uuid()),
            new LanguageName("mock")
        );
    }

    public static Language fromName(String name) {
        return Language.create(
            new LanguageId(faker.internet().uuid()),
            new LanguageName(name)
        );
    }
}