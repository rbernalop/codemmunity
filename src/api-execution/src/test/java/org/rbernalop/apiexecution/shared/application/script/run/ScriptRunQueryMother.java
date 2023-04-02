package org.rbernalop.apiexecution.shared.application.script.run;

import com.github.javafaker.Faker;

public class ScriptRunQueryMother {
    private final static Faker faker = new Faker();

    public static ScriptRunQuery randomByLanguageId(String id) {
        return new ScriptRunQuery(faker.lorem().sentence(), id);
    }
}