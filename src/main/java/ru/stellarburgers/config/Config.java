package ru.stellarburgers.config;

public class Config {
    public static final String BASE_URL = "https://stellarburgers.education-services.ru";
    public static final String API_BASE_URL = "https://stellarburgers.education-services.ru/api";
    
    public static final String MAIN_PAGE_URL = BASE_URL;
    public static final String LOGIN_PAGE_URL = BASE_URL + "/login";
    public static final String REGISTER_PAGE_URL = BASE_URL + "/register";
    public static final String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "/forgot-password";
    public static final String PROFILE_PAGE_URL = BASE_URL + "/account";
    
    public static final String REGISTER_API_ENDPOINT = "/auth/register";
    public static final String LOGIN_API_ENDPOINT = "/auth/login";
    public static final String LOGOUT_API_ENDPOINT = "/auth/logout";
    public static final String USER_API_ENDPOINT = "/auth/user";
}