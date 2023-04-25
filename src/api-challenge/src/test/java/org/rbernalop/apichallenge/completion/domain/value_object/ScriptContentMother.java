package org.rbernalop.apichallenge.completion.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

public class ScriptContentMother {
    public static ScriptContent random() {
        return new ScriptContent(Faker.instance().lorem().paragraph());
    }
}
