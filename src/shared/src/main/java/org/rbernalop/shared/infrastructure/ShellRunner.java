package org.rbernalop.shared.infrastructure;


import org.springframework.data.util.Pair;

import java.io.File;

public class ShellRunner {
    public static Pair<String, Boolean> executeCommand(String command) {
        return executeCommand(command, ".");
    }


    public static Pair<String, Boolean> executeCommand(String command, String directory) {
        String output = "";
        boolean success = false;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(command.split(" "));
            processBuilder.directory(new File(directory));
            Process process = processBuilder.start();
            process.waitFor();
            if (process.exitValue() != 0) {
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
