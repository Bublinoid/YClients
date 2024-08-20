package ru.bublinoid.yclientsbot;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YClientsBotApplication implements CommandLineRunner {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    public static void main(String[] args) {
        SpringApplication.run(YClientsBotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Инициализация Twilio клиента
        Twilio.init(accountSid, authToken);

        // Данные о записи клиента
        String procedureName = "Маникюр";
        String appointmentDate = "2024-08-20 14:00";
        String clientPhoneNumber = "whatsapp:+79104254267";

        // Формирование сообщения
        String messageBody = String.format(
                "Добрый день! Вы записаны на процедуру %s на время %s.\n" +
                        "Чтобы подтвердить запись, введите - 0.\n" +
                        "Если хотите отказаться, введите - 1.\n" +
                        "С уважением и пожеланием хорошего дня, TheNails.",
                procedureName, appointmentDate
        );

        // Отправка сообщения через WhatsApp
        Message message = Message.creator(
                new PhoneNumber(clientPhoneNumber),
                new PhoneNumber("whatsapp:+14155238886"),
                messageBody
        ).create();

        // Печать SID отправленного сообщения
        System.out.println("Message SID: " + message.getSid());
    }

}

//TODO: todo
