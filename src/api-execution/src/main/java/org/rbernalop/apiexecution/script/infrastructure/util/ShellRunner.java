package org.rbernalop.apiexecution.script.infrastructure.util;

public class ShellRunner {
    public static String executeCommand(String command) {
        String output = "";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            if(process.exitValue() != 0) {
                output = new String(process.getErrorStream().readAllBytes());
            } else {
                output = new String(process.getInputStream().readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
