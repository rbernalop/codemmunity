package org.rbernalop.apitest.test.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.shared.infrastructure.ShellRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.util.Pair;

@Configuration
@Slf4j
public class NodeJSTestºConfiguration {
    @Bean
    public CommandLineRunner installNodeJSCompiler() {
        return args -> {
            Pair<String, Boolean> nodeResult = ShellRunner.executeCommand("node -v");
            Boolean success = nodeResult.getSecond();
            if (!success) {
                log.warn("NodeJS is not installed.");
                log.info("Installing NodeJS...");
                ShellRunner.executeCommand("sudo apt-get install nodejs");
            }
            Pair<String, Boolean> npmResult = ShellRunner.executeCommand(
                System.getProperty("os.name").toLowerCase().contains("win") ? "npm.cmd -v" : "npm -v");
            success = npmResult.getSecond();
            if (!success) {
                log.warn("NPM is not installed.");
                log.info("Installing NPM...");
                ShellRunner.executeCommand("sudo apt-get install npm");
            }
            log.info("NodeJS and NPM are installed.");
        };
    }

    @Bean
    @DependsOn("installNodeJSCompiler")
    public CommandLineRunner installJest() {
        return args -> {
            String installCommand = System.getProperty("os.name").toLowerCase().contains("win")
                ? "npm.cmd install --save-dev jest" : "npm install --save-dev jest";
            ShellRunner.executeCommand(installCommand);
        };
    }
}
