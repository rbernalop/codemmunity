package org.rbernalop.apiexecution.script.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;

@Configuration
@Slf4j
public class PythonConfiguration {
    @Bean
    public CommandLineRunner installPythonCompiler() {
        return args -> {
            Pair<String, Boolean> result = ShellRunner.executeCommand("python3 -V");
            Boolean success = result.getSecond();
            if (!success) {
                log.warn("Python is not installed.");
                log.info("Installing Python...");
                ShellRunner.executeCommand("sudo apt-get install python3");
            }
            log.info("Python is installed.");
        };
    }
}
