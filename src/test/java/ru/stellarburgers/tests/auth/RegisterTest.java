package ru.stellarburgers.tests.auth;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stellarburgers.api.UserApi;
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
    public void testSuccessfulRegistration() {
        String testEmail = UserApi.generateEmail();
        String testPassword = UserApi.generatePassword();
        String testName = UserApi.generateName();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage()
                .enterName(testName)
                .enterEmail(testEmail)
                .enterPassword(testPassword)
                .clickRegisterButton();

        // проверка
        assertTrue(driver.getCurrentUrl().contains(Config.LOGIN_PAGE_URL),
                "После регистрации должна открыться страница логина");

        // удаление пользователя
        Response loginResp = UserApi.loginUser(testEmail, testPassword);
        String testToken = loginResp.jsonPath().getString("accessToken");
        UserApi.deleteUser("Bearer " + testToken);
    }


    @Test
    @DisplayName("Ошибка при невалидном пароле в форме регистрации")
    @Description("1. Открыть форму регистрации → 2. Ввести имя/email/невалидный пароль → 3. Клик кнопки → 4. Ошибка на RegisterPage")
    public void testErrorForInvalidPasswordInRegisterForm() {
        String testEmail = UserApi.generateEmail();
        String testName = UserApi.generateName();
        String invalidPassword = "123";  // < 6 символов

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage()
                .enterName(testName)
                .enterEmail(testEmail)
                .enterPassword(invalidPassword)
                .clickRegisterButtonWithoutWait();

        WebElement errorMsg = registerPage.waitForInputError();
        assertTrue(errorMsg.isDisplayed(), "Ошибка пароля должна быть видна");
        assertTrue(errorMsg.getText().contains("Некорректный пароль"),
                "Ошибка должна содержать 'Некорректный пароль'. Получено: " + errorMsg.getText());
    }
}
