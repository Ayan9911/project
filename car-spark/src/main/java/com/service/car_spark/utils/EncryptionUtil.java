package com.service.car_spark.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class EncryptionUtil {

    private static final String ALGO = "AES";
    private static final byte[] KEY = "MySecretAESKey12".getBytes(StandardCharsets.UTF_8); // Must be 16 bytes

    public static byte[] encrypt(byte[] data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] encryptedData) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(encryptedData);
    }
}
