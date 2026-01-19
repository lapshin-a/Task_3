package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.config.Config;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Локаторы
    private static final By LOGOUT_BUTTON = By.xpath("//button[contains(text(), 'Выход')]");
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//*[contains(text(), 'Конструктор')]/parent::*");

    @Step("Проверка загрузки страницы профиля")
    public boolean isProfilePageLoaded() {
        try {
            return isElementDisplayed(LOGOUT_BUTTON);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Клик на кнопку 'Конструктор' из профиля")
    public static MainPage clickConstructor() {
        try {
            // Вариант 1: Попытка кликнуть на "Конструктор"
            click(CONSTRUCTOR_BUTTON);
        } catch (Exception e1) {
            try {
                // Вариант 2: Если не сработало, ищем по более общему селектору
                By alternativeLocator = By.xpath("//p[contains(text(), 'Конструктор')]");
                click(alternativeLocator);
            } catch (Exception e2) {
                try {
                    // Вариант 3: Ищем по ссылке в левой панели
                    By alternativeLocator = By.xpath("//a[@href='/']");
                    click(alternativeLocator);
                } catch (Exception e3) {
                    throw new RuntimeException("Не удалось найти кнопку 'Конструктор'", e3);
                }
            }
        }

        // Явное ожидание загрузки главной страницы
        waitForUrlContains(Config.MAIN_PAGE_URL);
        return new MainPage(driver);
    }

    @Step("Клик на кнопку 'Выход'")
    public static LoginPage clickLogout() {
        click(LOGOUT_BUTTON);
        // Явное ожидание возврата на страницу входа
        waitForUrlContains(Config.LOGIN_PAGE_URL);
        return new LoginPage(driver);
    }
}
