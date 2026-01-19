package ru.stellarburgers.tests.auth;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.ProfilePage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты выхода из аккаунта")
public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверяет: регистрация → вход → личный кабинет → выход")
    public void testLogout() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. Вход
        loginPage.enterEmail(userEmail).enterPassword(userPassword);
        MainPage mainPage = loginPage.clickLoginButton();

        // 3. Переход в личный кабинет
        mainPage.clickProfile();

        // 4. Выход
        LoginPage logoutResult = ProfilePage.clickLogout();

        // 5. ПРОВЕРКА: Вернулись на страницу входа
        assertTrue(logoutResult.isLoginPageLoaded(),
                "После выхода должна загрузиться страница входа");
    }
}
