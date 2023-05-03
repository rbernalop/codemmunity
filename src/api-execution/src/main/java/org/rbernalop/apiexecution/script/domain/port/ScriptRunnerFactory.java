package org.rbernalop.apiexecution.script.domain.port;

import org.rbernalop.shared.domain.exception.FactoryException;

public class ScriptRunnerFactory {
    public static ScriptRunner get(String language) {
        String className =
            Character.toUpperCase(language.charAt(0)) + language.substring(1).toLowerCase() + "Runner";
        String packageName = "org.rbernalop.apiexecution.script.infrastructure.adapter.";
        try {
            Class<?> clazz = Class.forName(packageName + className);
            return (ScriptRunner) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new FactoryException("There's no script runner for " + className);
        }
    }
}
