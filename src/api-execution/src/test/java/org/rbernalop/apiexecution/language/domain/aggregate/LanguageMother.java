package org.rbernalop.apiexecution.language.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.apiexecution.language.domain.value_object.LanguageName;
import org.rbernalop.shared.domain.valueobject.LanguageId;

import java.util.List;

public class LanguageMother {
    private final static Faker faker = new Faker();

    public static Language random() {
        return new Language(
            new LanguageId(faker.internet().uuid()),
            new LanguageName(faker.lorem().word())
        );
    }

    public static List<Language> randomList() {
        return List.of(random());
    }

    public static Language mockLanguage() {
        return new Language(
            new LanguageId(faker.internet().uuid()),
            new LanguageName("mock")
        );
    }
}