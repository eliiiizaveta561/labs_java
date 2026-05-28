package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            String password = "AVbC1245";

            String regex = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);

            if (matcher.matches()) {
                logger.info("пароль корректный");
            } else {
                logger.warn("неверный пароль");
            }

            User user = new User("testUser", password);

            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(user);
            logger.info("Сериализованный JSON: {}", json);

            User deserializedUser = mapper.readValue(json, User.class);
            logger.info("Десериализован пользователь: {}, пароль: {}",
                    deserializedUser.getUsername(),
                    deserializedUser.getPassword());

        } catch (Exception e) {
            logger.error("Ошибка: " + e.getMessage(), e);
        }
    }
}