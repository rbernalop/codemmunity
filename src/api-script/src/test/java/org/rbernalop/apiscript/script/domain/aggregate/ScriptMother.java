package org.rbernalop.apiscript.script.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.apiscript.script.domain.value_object.*;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class ScriptMother {
    private final static Faker faker = new Faker();

    public static Script random() {
        return Script.create(
            new ScriptId(faker.internet().uuid()),
            new UserUsername(faker.name().username()),
            new ScriptName(faker.lorem().sentence()),
            new ShareKey(faker.internet().uuid()),
            new LanguageId(faker.internet().uuid())
        );
    }

    public static Script fromScriptAndNewName(Script script, String nextName) {
        return Script.create(
            script.getId(),
            new UserUsername(script.getOwnerName()),
            new ScriptName(nextName),
            new ShareKey(script.getShareKey()),
            new LanguageId(script.getLanguageId())
        );
    }

    public static Script randomWithId(String id) {
        return Script.create(
            new ScriptId(id),
            new UserUsername(faker.name().username()),
            new ScriptName(faker.lorem().sentence()),
            new ShareKey(faker.internet().uuid()),
            new LanguageId(faker.internet().uuid())
        );
    }

    public static Script fromScriptAndNewShareKey(Script script, String shareKey) {
        return Script.create(
            script.getId(),
            new UserUsername(script.getOwnerName()),
            new ScriptName(script.getName()),
            new ShareKey(shareKey),
            new LanguageId(script.getLanguageId())
        );
    }
}
