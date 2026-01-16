package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stellarburgers.config.Config;

public class RegisterPage extends BasePage {

    private static final String PAGE_URL = Config.REGISTER_PAGE_URL;

    private static final By NAME_INPUT =
            By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input[@type='text'][1]");
    private static final By EMAIL_INPUT =
            By.xpath("//label[contains(text(), 'Email')]/following-sibling::input[@type='text']");
    private static final By PASSWORD_INPUT =
            By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input[@type='password']");
    private static final By REGISTER_BUTTON =
            By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private static final By LOGIN_LINK =
            By.xpath("//a[contains(text(), 'Войти')]");
    public static final By ERROR_MESSAGE =
            By.xpath("//p[contains(text(), 'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы регистрации")
    public RegisterPage openRegisterPage() {
        navigateTo(PAGE_URL);
        // Явное ожидание загрузки страницы регистрации
        waitForUrlContains(Config.REGISTER_PAGE_URL);
        return this;
    }

    @Step("Ввод имени")
    public RegisterPage enterName(String name) {
        WebElement nameInput = waitForElement(NAME_INPUT);
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Ввод email")
    public RegisterPage enterEmail(String email) {
        WebElement emailInput = waitForElement(EMAIL_INPUT);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage enterPassword(String password) {
        WebElement passwordInput = waitForElement(PASSWORD_INPUT);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public LoginPage clickRegisterButton() {
        click(REGISTER_BUTTON);
        // Явное ожидание загрузки страницы входа
        waitForUrlContains(Config.LOGIN_PAGE_URL);
        return new LoginPage(driver);
    }

    @Step("Клик на ссылку 'Войти'")
    public LoginPage clickLoginLink() {
        click(LOGIN_LINK);
        return new LoginPage(driver);
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    @Step("Проверка наличия сообщения об ошибке 'Некорректный пароль'")
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }

    @Step("Проверка загрузки страницы регистрации")
    public boolean isRegisterPageLoaded() {
        return isElementDisplayed(REGISTER_BUTTON);
    }

    @Step("Проверка URL страницы регистрации")
    public void verifyRegisterPageUrl() {
        waitForUrlContains(Config.REGISTER_PAGE_URL);
    }
}
