package ru.stellarburgers.api;

public class UserApi {
    public static String generateEmail() {
        return "test_" + System.currentTimeMillis() + "@example.com";
    }

    public static String generatePassword() {
        return "password123";
    }

    public static String generateName() {
        return "Test User";
    }
}
