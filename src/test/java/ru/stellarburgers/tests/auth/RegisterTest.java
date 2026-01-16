package ru.stellarburgers.tests.auth;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.config.Config;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Register Tests")
public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("1. BaseTest создал пользователя → 2. Открыть login/register → 3. Залогинеться → 4. Главная страница")
    public void testSuccessfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();

        // КЛИК по "Зарегистрироваться"
        LoginPage loginPage = registerPage.clickLoginLink();

        // Логин
        loginPage.enterEmail(userEmail)
                .enterPassword(userPassword);

        MainPage mainPage = loginPage.clickLoginButton();
        assertTrue(driver.getCurrentUrl().contains(Config.MAIN_PAGE_URL),
                "Должны войти через кнопку входа в форме регистрации");
        assertFalse(driver.findElements(MainPage.LOGIN_BUTTON_MAIN).size() > 0, "Кнопка 'Войти' должна отсутствовать");
    }

    @Test
    @DisplayName("Ошибка при неверном пароле")
    @Description("1. BaseTest создал пользователя → 2. Неверный пароль → 3. Ошибка")
    public void testErrorForIncorrectPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        LoginPage loginPage = registerPage.clickLoginLink();

        loginPage.enterEmail(userEmail)
                .enterPassword("123");
        loginPage.clickLoginButton();

        // Проверяем сообщение "Некорректный пароль"
        assertTrue(registerPage.isErrorMessageDisplayed(),
                "Должно быть сообщение об ошибке 'Некорректный пароль'");

        // Проверяем точный текст ошибки
        String errorText = registerPage.getErrorMessage();
        assertTrue(errorText.contains("Некорректный пароль"),
                "Текст ошибки должен содержать 'Некорректный пароль', но получили: " + errorText);
    }
}
