package org.rbernalop.apiscript.shared.application.script.modify;

import com.github.javafaker.Faker;

public class RenameScriptCommandMother {
    private static final Faker faker = new Faker();

    public static RenameScriptCommand randomFromScriptIdAndUsername(String scriptId, String username) {
        return new RenameScriptCommand(
                scriptId,
                faker.name().name(),
                username
        );
    }

    public static RenameScriptCommand randomWithEmptyName() {
        return new RenameScriptCommand(
                faker.internet().uuid(),
                "",
                faker.name().username()
        );
    }

    public static RenameScriptCommand random() {
        return new RenameScriptCommand(
                faker.internet().uuid(),
                faker.name().name(),
                faker.name().username()
        );
    }
}