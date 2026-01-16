package ru.stellarburgers.tests.navigation;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.config.Config;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.ProfilePage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.stellarburgers.pages.BasePage.waitForUrlContains;

public class ProfileNavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверяет: регистрация → вход → переход в личный кабинет")
    public void testNavigateToProfile() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. Вход
        loginPage.enterEmail(userEmail).enterPassword(userPassword);
        MainPage mainPage = loginPage.clickLoginButton();
        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);

        // 3. Кликнуть на "Личный кабинет"
        mainPage.clickProfile();

        // Явное ожидание загрузки страницы профиля
        waitForUrlContains(Config.PROFILE_PAGE_URL);

        // 4. ПРОВЕРКА: Мы в личном кабинете
        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.isProfilePageLoaded(),
                "Должны быть в личном кабинете");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверяет: регистрация → вход → личный кабинет → конструктор")
    public void testNavigateFromProfileToConstructor() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. Вход
        loginPage.enterEmail(userEmail).enterPassword(userPassword);
        MainPage mainPage = loginPage.clickLoginButton();

        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);

        // 3. Переход в личный кабинет
        mainPage.clickProfile();

        // Явное ожидание загрузки страницы профиля
        waitForUrlContains(Config.PROFILE_PAGE_URL);

        // 4. Кликнуть "Конструктор" из профиля
        mainPage = ProfilePage.clickConstructor();

        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);

        // 5. ПРОВЕРКА: Вернулись в конструктор (проверяем URL)
        assertTrue(driver.getCurrentUrl().contains(Config.MAIN_PAGE_URL),
                "Должны вернуться в конструктор");
    }
}
