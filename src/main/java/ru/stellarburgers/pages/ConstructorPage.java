package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends BasePage {

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы вкладок
    private static final By BUNS_TAB = By.xpath("//div[contains(@class, 'tab')]//span[contains(text(), 'Булки')]");
    private static final By SAUCES_TAB = By.xpath("//div[contains(@class, 'tab')]//span[contains(text(), 'Соусы')]");
    private static final By FILLING_TAB = By.xpath("//div[contains(@class, 'tab')]//span[contains(text(), 'Начинки')]");

    // Локаторы активных вкладок - проверяем по классу tab_type_current
    public static final By BUNS_TAB_ACTIVE = By.xpath("//div[contains(@class, 'tab_type_current')]//span[contains(text(), 'Булки')]");
    public static final By SAUCES_TAB_ACTIVE = By.xpath("//div[contains(@class, 'tab_type_current')]//span[contains(text(), 'Соусы')]");
    public static final By FILLING_TAB_ACTIVE = By.xpath("//div[contains(@class, 'tab_type_current')]//span[contains(text(), 'Начинки')]");

    @Step("Клик на вкладку 'Булки'")
    public void clickBunsTab() {
        click(BUNS_TAB);
        // Явное ожидание активности вкладки
        waitForElement(BUNS_TAB_ACTIVE);
    }

    @Step("Клик на вкладку 'Соусы'")
    public void clickSaucesTab() {
        click(SAUCES_TAB);
        // Явное ожидание активности вкладки
        waitForElement(SAUCES_TAB_ACTIVE);
    }

    @Step("Клик на вкладку 'Начинки'")
    public void clickFillingTab() {
        click(FILLING_TAB);
        // Явное ожидание активности вкладки
        waitForElement(FILLING_TAB_ACTIVE);
    }

    @Step("Проверка: вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        return isElementDisplayed(BUNS_TAB_ACTIVE);
    }

    @Step("Проверка: вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        return isElementDisplayed(SAUCES_TAB_ACTIVE);
    }

    @Step("Проверка: вкладка 'Начинки' активна")
    public boolean isFillingTabActive() {
        return isElementDisplayed(FILLING_TAB_ACTIVE);
    }
}
