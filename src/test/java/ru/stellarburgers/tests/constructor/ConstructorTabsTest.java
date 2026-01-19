package ru.stellarburgers.tests.constructor;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.config.Config;
import ru.stellarburgers.pages.ConstructorPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.stellarburgers.pages.BasePage.waitForUrlContains;

@DisplayName("Тесты раздела Конструктор")
public class ConstructorTabsTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Проверяет работу перехода к разделу Булки")
    public void testNavigateToBuns() {
        // 1. ВХОД - через главную страницу
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        // 2. Вход
        loginPage.enterEmail(userEmail).enterPassword(userPassword);
        MainPage mainPage = loginPage.clickLoginButton();

        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);

        // 3. Клик на вкладку "Соусы" (чтобы убрать focus с Булок)
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();

        // 4. Клик на вкладку "Булки"
        constructorPage.clickBunsTab();

        // Явное ожидание активности вкладки
        constructorPage.waitForElement(constructorPage.BUNS_TAB_ACTIVE);

        // 5. ПРОВЕРКА: Вкладка "Булки" активна
        assertTrue(constructorPage.isBunsTabActive(),
                "Вкладка 'Булки' должна быть активной");
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверяет работу перехода к разделу Соусы")
    public void testNavigateToSauces() {
        // 1. Регистрация
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@test.com";
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.enterName("Test User")
                .enterEmail(uniqueEmail)
                .enterPassword("password123");
        LoginPage loginPage = registerPage.clickRegisterButton();

        // 2. Вход
        loginPage.enterEmail(userEmail).enterPassword(userPassword);
        MainPage mainPage = loginPage.clickLoginButton();

        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);

        // 3. Клик на вкладку "Соусы"
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();

        // Явное ожидание активности вкладки
        constructorPage.waitForElement(constructorPage.SAUCES_TAB_ACTIVE);

        // 4. ПРОВЕРКА: Вкладка "Соусы" активна
        assertTrue(constructorPage.isSaucesTabActive(),
                "Вкладка 'Соусы' должна быть активной");
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверяет работу перехода к разделу Начинки")
    public void testNavigateToFilling() {
        // 1. Регистрация
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@test.com";
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.enterName("Test User")
                .enterEmail(uniqueEmail)
                .enterPassword("password123");
        LoginPage loginPage = registerPage.clickRegisterButton();

        // 2. Вход
        loginPage.enterEmail(userEmail).enterPassword(userPassword);
        MainPage mainPage = loginPage.clickLoginButton();

        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);

        // 3. Клик на вкладку "Начинки"
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickFillingTab();

        // Явное ожидание активности вкладки
        constructorPage.waitForElement(constructorPage.FILLING_TAB_ACTIVE);

        // 4. ПРОВЕРКА: Вкладка "Начинки" активна
        assertTrue(constructorPage.isFillingTabActive(),
                "Вкладка 'Начинки' должна быть активной");
    }
}
