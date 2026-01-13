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
        // 1. Регистрация
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@test.com";
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.enterName("Test User")
                .enterEmail(uniqueEmail)
                .enterPassword("password123");
        LoginPage loginPage = registerPage.clickRegisterButton();

        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 2. Вход
        loginPage.enterEmail(uniqueEmail).enterPassword("password123");
        MainPage mainPage = loginPage.clickLoginButton();

        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 3. Переход в личный кабинет
        mainPage.clickProfile();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 4. Выход
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage logoutResult = profilePage.clickLogout();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 5. ПРОВЕРКА: Вернулись на страницу входа
        assertTrue(logoutResult.isLoginPageLoaded(),
                "После выхода должна загрузиться страница входа");
    }
}
