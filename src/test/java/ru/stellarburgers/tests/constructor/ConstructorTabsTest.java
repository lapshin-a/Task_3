package ru.stellarburgers.tests.constructor;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stellarburgers.pages.ConstructorPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты раздела Конструктор")
public class ConstructorTabsTest extends BaseTest {

    private static final String TEST_EMAIL = "testuser@yandex.ru";
    private static final String TEST_PASSWORD = "password123";

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Проверяет работу перехода к разделу Булки")
    public void testNavigateToBuns() {
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

        // 3. Клик на вкладку "Соусы" (чтобы убрать focus с Булок)
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();

        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 4. Клик на вкладку "Булки"
        constructorPage.clickBunsTab();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

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

        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 2. Вход
        loginPage.enterEmail(uniqueEmail).enterPassword("password123");
        MainPage mainPage = loginPage.clickLoginButton();

        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 3. Клик на вкладку "Соусы"
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

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

        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 2. Вход
        loginPage.enterEmail(uniqueEmail).enterPassword("password123");
        MainPage mainPage = loginPage.clickLoginButton();

        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 3. Клик на вкладку "Начинки"
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickFillingTab();

        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // 4. ПРОВЕРКА: Вкладка "Начинки" активна
        assertTrue(constructorPage.isFillingTabActive(),
                "Вкладка 'Начинки' должна быть активной");
    }
}
