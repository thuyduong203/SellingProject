package com.hyperlogy_ban_hang_2.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            //Tạo một đối tượng KeyPairGenerator sử dụng thuật toán RSA bằng cách sử dụng phương thức getInstance("RSA").
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //thiết lập kích thước khóa là 2048 bit bằng cách sử dụng phương thức initialize(2048) của KeyPairGenerator.
            keyPairGenerator.initialize(2048);
            //Gọi phương thức generateKeyPair() để sinh ra một cặp khóa RSA,
            // gồm khóa công khai (PublicKey) và khóa bí mật (PrivateKey).
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyPair;
    }
}
