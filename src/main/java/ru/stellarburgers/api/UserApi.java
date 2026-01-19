package ru.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.stellarburgers.config.Config;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String REGISTER_ENDPOINT = Config.REGISTER_API_ENDPOINT;
    private static final String USER_ENDPOINT = Config.USER_API_ENDPOINT;

    static {
        RestAssured.baseURI = Config.API_BASE_URL;
    }

    public static String generateEmail() {
        return "test_" + System.currentTimeMillis() + "@example.com";
    }

    public static String generatePassword() {
        return "password123";
    }

    public static String generateName() {
        return "Test User_" + System.currentTimeMillis();
    }

    public static Response registerUser(String email, String password, String name) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", name);

        return given()
                .contentType(ContentType.JSON)
                .body(userData)
                .post(REGISTER_ENDPOINT);
    }

    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(USER_ENDPOINT);
    }

    public static Response loginUser(String email, String password) {
        Map userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);

        return given()
                .contentType(ContentType.JSON)
                .body(userData)
                .post(Config.LOGIN_API_ENDPOINT);
    }
}