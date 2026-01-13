package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MainPage extends BasePage {
    private static final String PAGE_URL = "https://stellarburgers.education-services.ru";

    private static final By MAIN_PAGE_TITLE =
            By.xpath("//h1[contains(text(), 'Конструктор')]");

    private static final By LOGIN_BUTTON_MAIN =
            By.xpath("//button[contains(text(), 'Войти в аккаунт')]");

    // Ищем SVG иконку профиля в ссылке с href="/account"
    private static final By PERSONAL_ACCOUNT_BUTTON =
            By.xpath("//a[@href='/account' and contains(@class, 'AppHeader_header_link')]");

    // Резервный локатор - если основной не сработает
    private static final By PERSONAL_ACCOUNT_BUTTON_BACKUP =
            By.xpath("//a[@href='/account']//svg");

    private static final By CONSTRUCTOR_LINK_NAV =
            By.xpath("//a[contains(text(), 'Конструктор')]");

    private static final By LOGO_LINK =
            By.xpath("//a[contains(@href, 'stellarburgers')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие главной страницы")
    public MainPage openMainPage() {
        navigateTo(PAGE_URL);
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return this;
    }

    @Step("Проверка загрузки главной страницы")
    public boolean isMainPageLoaded() {
        return isElementDisplayed(MAIN_PAGE_TITLE) || isElementDisplayed(LOGIN_BUTTON_MAIN) || isElementDisplayed(PERSONAL_ACCOUNT_BUTTON);
    }

    @Step("Клик на кнопку 'Войти в аккаунт' на главной")
    public LoginPage clickLoginViaMainButton() {
        click(LOGIN_BUTTON_MAIN);
        return new LoginPage(driver);
    }

    @Step("Клик на 'Личный кабинет' для входа (когда не авторизован)")
    public LoginPage clickPersonalAccountToLogin() {
        clickProfileButton();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return new LoginPage(driver);
    }

    @Step("Клик на 'Личный кабинет' (когда авторизован)")
    public ProfilePage clickProfile() {
        clickProfileButton();
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return new ProfilePage(driver);
    }

    /**
     * ИСПРАВЛЕННЫЙ МЕТОД: Правильный клик на иконку профиля
     * Использует WebDriverWait + Actions только на Java
     */
    private void clickProfileButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement profileLink = null;

        try {
            // Способ 1: Пытаемся найти ссылку с классом AppHeader_header_link
            profileLink = wait.until(
                    ExpectedConditions.elementToBeClickable(PERSONAL_ACCOUNT_BUTTON)
            );
        } catch (Exception e1) {
            try {
                // Способ 2: Если не найдена - ищем просто ссылку и ее родителя
                WebElement svgElement = wait.until(
                        ExpectedConditions.elementToBeClickable(PERSONAL_ACCOUNT_BUTTON_BACKUP)
                );
                profileLink = svgElement;
            } catch (Exception e2) {
                // Способ 3: Ищем любую ссылку с href=/account и берем родителя
                profileLink = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@href='/account']")
                        )
                );
            }
        }

        // Теперь кликаем на найденный элемент
        try {
            // Попытка 1: Обычный клик
            profileLink.click();
        } catch (Exception e) {
            try {
                // Попытка 2: Actions с наведением
                Actions actions = new Actions(driver);
                actions.moveToElement(profileLink).click().perform();
            } catch (Exception e2) {
                try {
                    // Попытка 3: Найти ссылку и кликнуть на нее
                    WebElement link = driver.findElement(By.xpath("//a[@href='/account']"));
                    wait.until(ExpectedConditions.visibilityOf(link));
                    link.click();
                } catch (Exception e3) {
                    // Последняя попытка - повторный клик
                    profileLink.click();
                }
            }
        }
    }

    @Step("Клик на ссылку 'Конструктор'")
    public MainPage clickConstructorLink() {
        click(CONSTRUCTOR_LINK_NAV);
        return this;
    }

    @Step("Клик на логотип")
    public MainPage clickLogo() {
        click(LOGO_LINK);
        return this;
    }
}