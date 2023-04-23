package org.rbernalop.apitest.test.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.shared.infrastructure.ShellRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;

@Configuration
@Slf4j
public class JavaTestConfiguration {
    @Bean
    public CommandLineRunner installJavaCompiler() {
        return args -> {
            Pair<String, Boolean> result = ShellRunner.executeCommand("java --version");
            Boolean success = result.getSecond();
            if (!success) {
                log.warn("Java is not installed.");
                log.info("Installing Java...");
                ShellRunner.executeCommand("sudo apt-get install openjdk-11-jdk");
            }
            log.info("Java is installed.");
        };
    }

    @Bean
    public CommandLineRunner getJunitJar() {
        return args -> {
            String junitVersion = "1.7.2";
            String junitJar = "junit-platform-console-standalone-" + junitVersion +  ".jar";
            Pair<String, Boolean> result = ShellRunner.executeCommand("ls " + junitJar);
            Boolean success = result.getSecond();
            if (!success) {
                log.warn("Junit is not installed.");
                log.info("Installing Junit...");
                ShellRunner.executeCommand("curl -o " + junitJar + " https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/" + junitVersion + "/" + junitJar);
            }
            log.info("Junit is installed.");
        };
    }
}
