package org.rbernalop.apiexecution.script.infrastructure.util;

public class CommandLineRunner {
    public static String executeCommand(String command) {
        String output = "";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            output = new String(process.getInputStream().readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
