package org.rbernalop.apiexecution.script.infrastructure.util;


import org.springframework.data.util.Pair;

public class ShellRunner {
    public static Pair<String, Boolean> executeCommand(String command) {
        String output = "";
        boolean success = false;
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            if(process.exitValue() != 0) {
                output = new String(process.getErrorStream().readAllBytes());
            } else {
                output = new String(process.getInputStream().readAllBytes());
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Pair.of(output, success);
    }
}
