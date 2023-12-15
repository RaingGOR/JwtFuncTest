package ru.Raingor.webAnimeSite.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
//    public static void main(String[] args) {
//        // Генерируем случайный байтовый массив
//        byte[] secretBytes = generateRandomBytes();
//
//        // Кодируем байтовый массив в Base64
//        String jwtSecret = Base64.getEncoder().encodeToString(secretBytes);
//
//        System.out.println("Generated JWT Secret: " + jwtSecret);
//    }

    private static byte[] generateRandomBytes() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32]; // Выберите длину ключа в соответствии с требованиями вашего приложения
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }
}
