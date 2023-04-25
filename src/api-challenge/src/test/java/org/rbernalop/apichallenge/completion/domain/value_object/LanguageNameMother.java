package org.rbernalop.apichallenge.completion.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.LanguageName;

public class LanguageNameMother {
    public static LanguageName random() {
        return new LanguageName(Faker.instance().lorem().word());
    }
}
