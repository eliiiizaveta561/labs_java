package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import java.util.Scanner;
import java.util.Properties;
import java.io.InputStream;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("The program is running");

        readBuildPassport();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter stroka: ");

        if (scanner.hasNextLine()){
            String input = scanner.nextLine();
            log.debug("The user entered: {}", input);
            //используем метод из Apache Commons
            String reversed = StringUtils.reverse(input);
            String capitalized = StringUtils.capitalize(reversed);

            log.info("Processing result: {}", capitalized);
        }

        else{
            log.warn("No input received");
            System.out.println("No input received. Please eun with: ./gradlew run < input.txt");
        }

        log.info("The program is completed");

    }

    private static void readBuildPassport() {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("build-passport.properties")) {
            if (input != null) {
                Properties props = new Properties();
                props.load(input);

                log.info("Assembly passport:");
                log.info("Greeting: {}", props.getProperty("welcome.message"));
                log.info("User: {}", props.getProperty("user.name"));
                log.info("OS: {}", props.getProperty("os.name"));
                log.info("JAVA: {}", props.getProperty("java.version"));
                log.info("Assembly time: {}", props.getProperty("build.time"));
            } else {
                log.warn("File build-passport.properties not found");
            }
        } catch (Exception e) {
            log.error("Error when reading thr assembly passport", e);
        }
    }
}
