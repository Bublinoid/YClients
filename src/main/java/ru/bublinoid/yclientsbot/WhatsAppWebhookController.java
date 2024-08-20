package ru.bublinoid.yclientsbot;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class WhatsAppWebhookController {

    @PostMapping("/whatsapp-webhook")
    public String handleIncomingMessage(@RequestParam("Body") String body, @RequestParam("From") String from) {
        // Логика обработки сообщения
        System.out.println("Received message from " + from + ": " + body);

        String responseMessage;
        if (body.equals("0")) {
            responseMessage = "Спасибо, ваша запись подтверждена!";
        } else if (body.equals("1")) {
            responseMessage = "Запись отменена.";
        } else {
            responseMessage = "Извините, я не понимаю ваш запрос. Введите 0 для подтверждения или 1 для отмены.";
        }

        // Создание ответа пользователю
        com.twilio.twiml.messaging.Message sms = new com.twilio.twiml.messaging.Message.Builder()
                .body(new Body.Builder(responseMessage).build())
                .build();

        MessagingResponse twiml = new MessagingResponse.Builder()
                .message(sms)
                .build();

        return twiml.toXml();
    }
}
