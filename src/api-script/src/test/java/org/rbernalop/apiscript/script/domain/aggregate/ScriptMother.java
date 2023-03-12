package org.rbernalop.apiscript.script.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.apiscript.script.domain.value_object.*;

public class ScriptMother {
    private final static Faker faker = new Faker();

    public static Script random() {
        return new Script(
            new ScriptId(faker.internet().uuid()),
            new OwnerUsername(faker.name().username()),
            new ScriptName(faker.lorem().sentence()),
            new ShareKey(faker.internet().uuid()),
            new ScriptLanguageId(faker.internet().uuid())
        );
    }

    public static Script fromScriptAndNewName(Script script, String nextName) {
        return new Script(
            script.getId(),
            new OwnerUsername(script.getOwnerName()),
            new ScriptName(nextName),
            new ShareKey(script.getShareKey()),
            new ScriptLanguageId(script.getLanguageId())
        );
    }
}
