package ru.bublinoid.yclientsbot;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YClientsBotApplication {

    public static final String ACCOUNT_SID = "ACa8c327d70806cda534c2ec4b1782d657";
    public static final String AUTH_TOKEN = "6a3c5026c13bf22963cfd159bbb2c7ee";

    public static void main(String[] args) {
        // Запуск Spring Boot приложения
        SpringApplication.run(YClientsBotApplication.class, args);

        // Отправка сообщения через Twilio после запуска приложения
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String procedureName = "Маникюр";
        String appointmentDate = "2024-08-20 14:00";
        String clientPhoneNumber = "whatsapp:+420721523596";

        String messageBody = String.format(
                "Добрый день! Вы записаны на процедуру %s на время %s.\n" +
                        "Чтобы подтвердить запись, введите - 0.\n" +
                        "Если хотите отказаться, введите - 1.\n" +
                        "С уважением и пожеланием хорошего дня, TheNails.",
                procedureName, appointmentDate
        );

        Message message = Message.creator(
                new PhoneNumber(clientPhoneNumber),
                new PhoneNumber("whatsapp:+14155238886"),
                messageBody
        ).create();

        System.out.println("Message SID: " + message.getSid());
    }
}