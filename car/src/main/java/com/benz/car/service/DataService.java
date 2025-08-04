package com.benz.car.service;

import com.benz.car.dto.UserRequestDTO;
import com.service.car.proto.UserDataProto.UserData;
import com.benz.car.utils.EncryptionUtil;
import com.benz.car.utils.ProtobufUtil;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

@Service
public class DataService {

    public void store(UserRequestDTO dto, String fileType) {
        try {
            validate(dto);

            // Convert to Proto
            UserData proto = ProtobufUtil.toProto(dto, fileType);

            // Encrypt
            byte[] encrypted = EncryptionUtil.encrypt(proto.toByteArray());

            // Save to binary file
            Path path = Paths.get("queue/input.bin"); // use "queue" folder in project root
            Files.createDirectories(path.getParent()); // ensure folder exists
            Files.write(path, encrypted);

        } catch (Exception e) {
            throw new RuntimeException("Store failed: " + e.getMessage());
        }
    }

    public void update(UserRequestDTO dto, String fileType) {
        // For now, same as store (or can use different file)
        store(dto, fileType);
    }

    public String read(String fileType) {
        try {
            Path path = Paths.get("queue/input.bin"); // match this to store()
            if (!Files.exists(path)) {
                throw new RuntimeException("input.bin not found — please store data first.");
            }

            byte[] encrypted = Files.readAllBytes(path);
            byte[] decrypted = EncryptionUtil.decrypt(encrypted);
            UserData data = UserData.parseFrom(decrypted);
            return data.toString();

        } catch (Exception e) {
            throw new RuntimeException("Read failed: " + e.getMessage());
        }
    }

    // ✅ Add this method to avoid "cannot find symbol" error
    private void validate(UserRequestDTO dto) {
        if (dto.getName() == null || dto.getDob() == null || dto.getSalary() == null || dto.getAge() == 0) {
            throw new IllegalArgumentException("Missing required fields.");
        }
    }
}