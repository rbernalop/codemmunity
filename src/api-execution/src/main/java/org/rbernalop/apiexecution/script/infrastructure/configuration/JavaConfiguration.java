package org.rbernalop.apiexecution.script.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JavaConfiguration {
    @Bean
    public CommandLineRunner installJavaCompiler() {
        return args -> {
            String output = ShellRunner.executeCommand("java --version");
            if (output.isEmpty()) {
                log.warn("Java is not installed.");
                log.info("Installing Java...");
                ShellRunner.executeCommand("sudo apt-get install openjdk-11-jdk");
            }
            log.info("Java is installed.");
        };
    }
}
