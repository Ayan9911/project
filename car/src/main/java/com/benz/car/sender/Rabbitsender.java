package com.benz.car.sender;

import jakarta.annotation.PostConstruct;
import com.service.car.proto.UserDataProto;
import com.benz.car.utils.EncryptionUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Rabbitsender {

    private final RabbitTemplate rabbitTemplate;

    public Rabbitsender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTestMessage() {
        try {
            // Create protobuf message
            UserDataProto.UserData userData = UserDataProto.UserData.newBuilder()
                    .setName("ayan")
                    .setDob("2000-01-01")
                    .setSalary(75000.0)
                    .setAge(24)
                    .setFileType("pdf")
                    .build();

            // Encrypt the bytes
            byte[] encrypted = EncryptionUtil.encrypt(userData.toByteArray());

            // Send to RabbitMQ queue
            rabbitTemplate.convertAndSend("car_queue", encrypted);
            System.out.println("✅ Sent message to car_queue!");

        } catch (Exception e) {
            System.err.println("❌ Failed to send: " + e.getMessage());
        }
    }

    // ✅ This will run automatically on application startup
    @PostConstruct
    public void init() {
        sendTestMessage();
    }
}