package org.rbernalop.servicesynchronization.script.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.shared.domain.valueobject.ScriptId;

public class ScriptMother {
    public static Script random() {
        return Script.create(
            new ScriptId(Faker.instance().internet().uuid()),
            new ScriptContent(Faker.instance().lorem().paragraph())
        );
    }
}