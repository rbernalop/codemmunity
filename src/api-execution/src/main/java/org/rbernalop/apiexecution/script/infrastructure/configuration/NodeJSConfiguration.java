package org.rbernalop.apiexecution.script.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;

@Configuration
@Slf4j
public class NodeJSConfiguration {
    @Bean
    public CommandLineRunner installNodeJSCompiler() {
        return args -> {
            Pair<String, Boolean> result = ShellRunner.executeCommand("node -v");
            Boolean success = result.getSecond();
            if (!success) {
                log.warn("NodeJS is not installed.");
                log.info("Installing NodeJS...");
                ShellRunner.executeCommand("sudo apt-get install nodejs");
                ShellRunner.executeCommand("sudo apt-get install npm");
            }
            log.info("NodeJS is installed.");
        };
    }
}
