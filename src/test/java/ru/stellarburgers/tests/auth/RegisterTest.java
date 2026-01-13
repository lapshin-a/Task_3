package ru.stellarburgers.tests.auth;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты регистрации")
public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяет успешную регистрацию пользователя")
    public void testSuccessfulRegistration() {
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@test.com";

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();

        assertTrue(registerPage.isRegisterPageLoaded(),
                "Должны быть на странице регистрации");

        registerPage
                .enterName("Test User")
                .enterEmail(uniqueEmail)
                .enterPassword("password123");

        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        LoginPage loginPage = registerPage.clickRegisterButton();

        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        assertTrue(loginPage.isLoginPageLoaded(),
                "После успешной регистрации должны быть на странице входа");
    }

    @Test
    @DisplayName("Ошибку для некорректного пароля")
    @Description("Проверяет, что система выдает ошибку 'Некорректный пароль' для пароля менее 6 символов")
    public void testErrorForIncorrectPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();

        registerPage
                .enterName("Test User")
                .enterEmail("test@test.com")
                .enterPassword("123");  // Менее 6 символов

        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        registerPage.clickRegisterButton();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Проверяем сообщение "Некорректный пароль"
        assertTrue(registerPage.isErrorMessageDisplayed(),
                "Должно быть сообщение об ошибке 'Некорректный пароль'");

        // Проверяем точный текст ошибки
        String errorText = registerPage.getErrorMessage();
        assertTrue(errorText.contains("Некорректный пароль"),
                "Текст ошибки должен содержать 'Некорректный пароль', но получили: " + errorText);
    }
}