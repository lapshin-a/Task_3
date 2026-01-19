package ru.stellarburgers.tests.auth;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.config.Config;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты входа в аккаунт")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверяет вход через кнопку входа на главной странице")
    public void testLoginViaMainPageButton() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. ВХОД - через главную страницу
        loginPage.enterEmail(userEmail).enterPassword(userPassword);

        // 3. ПРОВЕРКА - успешно вошли в систему
        assertTrue(driver.getCurrentUrl().contains(Config.MAIN_PAGE_URL),
                "Должны успешно войти через кнопку 'Войти в аккаунт'");
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет вход через кнопку входа в форме регистрации")
    public void testLoginViaRegistrationForm() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. ПЕРЕХОД В ФОРМУ ВХОДА - из формы регистрации кликаем "Войти"
        loginPage.enterEmail(userEmail).enterPassword(userPassword);

        // 3. ПРОВЕРКА - успешно вошли в систему
        assertTrue(driver.getCurrentUrl().contains(Config.MAIN_PAGE_URL),
                "Должны войти через кнопку входа в форме регистрации");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяет вход через кнопку входа в форме восстановления пароля")
    public void testLoginViaForgotPasswordForm() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. КЛИК НА "ЗАБЫЛИ ПАРОЛЬ?" - переходим на форму восстановления
        // и затем на форму входа через кнопку "Войти"
        loginPage.clickForgotPassword();

        // ожидание загрузки страницы восстановления пароля
        loginPage.waitForUrlContains("forgot-password");

        // 3. ВОЗВРАТ В ФОРМУ ВХОДА - кликаем кнопку входа в форме восстановления
        loginPage.clickLoginLink();

        // ожидание возврата на страницу входа
        loginPage.waitForUrlContains("login");

        // 4. ВХОД - используем данные пользователя
        loginPage.enterEmail(userEmail).enterPassword(userPassword);

        // 5. ПРОВЕРКА - успешно вошли в систему
        assertTrue(driver.getCurrentUrl().contains(Config.MAIN_PAGE_URL),
                "Должны войти через кнопку в форме восстановления пароля");
    }

    @Test
    @DisplayName("Вход с корректными данными")
    @Description("Проверяет базовый вход в аккаунт с корректными учётными данными")
    public void testLoginWithCorrectCredentials() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. ВХОД - логинимся с созданными учётными данными
        loginPage.enterEmail(userEmail).enterPassword(userPassword);

        // 3. ПРОВЕРКА - убеждаемся, что успешно вошли в систему
        assertTrue(driver.getCurrentUrl().contains(Config.MAIN_PAGE_URL),
                "Должны успешно войти в аккаунт с корректными данными");
    }
}
