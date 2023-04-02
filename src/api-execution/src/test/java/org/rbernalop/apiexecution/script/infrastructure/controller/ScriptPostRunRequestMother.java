package org.rbernalop.apiexecution.script.infrastructure.controller;

public class ScriptPostRunRequestMother {
    public static ScriptPostRunRequest pythonCode(String languageId) {
        return new ScriptPostRunRequest(
            "print('Hello world')",
            languageId
        );
    }

    public static ScriptPostRunRequest nodeJsCode(String value) {
        return new ScriptPostRunRequest(
            """
                console.log('Hello world')
            """,
            value
        );
    }

    public static ScriptPostRunRequest javaCode(String value) {
        return new ScriptPostRunRequest(
        """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Hello world");
                    }
                }""",
            value
        );
    }

    public static ScriptPostRunRequest javaCodeWithSyntaxError(String value) {
        return new ScriptPostRunRequest(
        """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Hello world")
                    }
                }""",
            value
        );
    }

    public static ScriptPostRunRequest javaCodeWithRuntimeError(String value) {
        return new ScriptPostRunRequest(
        """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println(1/0);
                    }
                }""",
            value
        );
    }
}
