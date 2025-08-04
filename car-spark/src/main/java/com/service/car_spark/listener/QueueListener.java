package com.service.car_spark.listener;

import com.google.protobuf.InvalidProtocolBufferException;
import com.service.car_spark.proto.UserDataProto;
import com.service.car_spark.utils.EncryptionUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @RabbitListener(queues = "car_queue")
    public void receiveMessage(byte[] encryptedMessage) {
        try {
            // Decrypt the message
            byte[] decrypted = EncryptionUtil.decrypt(encryptedMessage);

            // Parse using protobuf
            UserDataProto.UserData userData = UserDataProto.UserData.parseFrom(decrypted);

            // Print the data
            System.out.println("🚗 Received car data:");
            System.out.println("Name: " + userData.getName());
            System.out.println("DOB: " + userData.getDob());
            System.out.println("Salary: " + userData.getSalary());
            System.out.println("Age: " + userData.getAge());
            System.out.println("File Type: " + userData.getFileType());

        } catch (InvalidProtocolBufferException e) {
            System.err.println("❌ Failed to parse protobuf: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ General error: " + e.getMessage());
        }
    }
}
