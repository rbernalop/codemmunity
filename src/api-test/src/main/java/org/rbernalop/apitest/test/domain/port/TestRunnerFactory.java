package org.rbernalop.apitest.test.domain.port;

import org.rbernalop.shared.domain.exception.FactoryException;

public class TestRunnerFactory {
    public static TestRunner get(String language) {
        String className =
            Character.toUpperCase(language.charAt(0)) + language.substring(1).toLowerCase() + "TestRunner";
        String packageName = "org.rbernalop.apitest.test.infrastructure.adapter.";
        try {
            Class<?> clazz = Class.forName(packageName + className);
            return (TestRunner) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new FactoryException("There's no script runner for " + className);
        }
    }
}
