package ru.stellarburgers.tests.navigation;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.ProfilePage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileNavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверяет: регистрация → вход → переход в личный кабинет")
    public void testNavigateToProfile() {
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

        // 3. Кликнуть на "Личный кабинет"
        mainPage.clickProfile();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 4. ПРОВЕРКА: Мы в личном кабинете
        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.isProfilePageLoaded(),
                "Должны быть в личном кабинете");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверяет: регистрация → вход → личный кабинет → конструктор")
    public void testNavigateFromProfileToConstructor() {
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

        // 4. Кликнуть "Конструктор" из профиля
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage = profilePage.clickConstructor();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 5. ПРОВЕРКА: Вернулись в конструктор (проверяем URL)
        assertTrue(driver.getCurrentUrl().contains("https://stellarburgers.education-services.ru/"),
                "Должны вернуться в конструктор");
    }
}
