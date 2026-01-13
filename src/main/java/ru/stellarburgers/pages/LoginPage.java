package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final String PAGE_URL = "https://stellarburgers.education-services.ru/login";

    private static final By EMAIL_INPUT =
            By.xpath("//label[contains(text(), 'Email')]/following-sibling::input[@type='text']");

    private static final By PASSWORD_INPUT =
            By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input[@type='password']");

    private static final By LOGIN_SUBMIT_BUTTON =
            By.xpath("//button[contains(text(), 'Войти')]");

    private static final By REGISTER_LINK =
            By.xpath("//a[contains(text(), 'Зарегистрироваться')]");

    private static final By FORGOT_PASSWORD_LINK =
            By.xpath("//a[contains(text(), 'Восстановить пароль')]");

    private static final By LOGIN_LINK_IN_FORGOT_PASSWORD =
            By.xpath("//a[contains(text(), 'Войти')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы входа")
    public LoginPage openLoginPage() {
        navigateTo(PAGE_URL);
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return this;
    }

    @Step("Ввод email")
    public LoginPage enterEmail(String email) {
        sendKeys(EMAIL_INPUT, email);
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        sendKeys(PASSWORD_INPUT, password);
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return this;
    }

    @Step("Клик на кнопку 'Войти'")
    public MainPage clickLoginButton() {
        scrollElementIntoView(LOGIN_SUBMIT_BUTTON);
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        click(LOGIN_SUBMIT_BUTTON);
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return new MainPage(driver);
    }

    @Step("Переход на страницу регистрации")
    public RegisterPage clickRegisterLink() {
        click(REGISTER_LINK);
        return new RegisterPage(driver);
    }

    @Step("Клик на ссылку 'Восстановить пароль'")
    public LoginPage clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
        return this;
    }

    @Step("Клик на ссылку 'Забыли пароль?'")
    public LoginPage clickForgotPassword() {
        click(FORGOT_PASSWORD_LINK);
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return this;
    }

    @Step("Клик на ссылку 'Войти' в форме восстановления пароля")
    public LoginPage clickLoginLink() {
        click(LOGIN_LINK_IN_FORGOT_PASSWORD);
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return this;
    }

    @Step("Переход на вход из формы восстановления пароля")
    public LoginPage clickLoginFromForgotPassword() {
        // На странице восстановления пароля есть кнопка 'Войти'
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        return loginPage;
    }

    @Step("Проверка загрузки страницы входа")
    public boolean isLoginPageLoaded() {
        return isElementDisplayed(LOGIN_SUBMIT_BUTTON);
    }

    @Step("Проверка URL страницы входа")
    public void verifyLoginPageUrl() {
        waitForUrlContains("/login");
    }
}
