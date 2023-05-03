package org.rbernalop.servicesynchronization.script.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.ShareKey;

public class ScriptMother {
    public static Script random() {
        return Script.create(
            new ScriptId(Faker.instance().internet().uuid()),
            new ShareKey(Faker.instance().internet().uuid()),
            new ScriptContent(Faker.instance().lorem().paragraph())
        );
    }
}