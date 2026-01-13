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
    private static final By BUNS_TAB_ACTIVE = By.xpath("//div[contains(@class, 'tab_type_current')]//span[contains(text(), 'Булки')]");
    private static final By SAUCES_TAB_ACTIVE = By.xpath("//div[contains(@class, 'tab_type_current')]//span[contains(text(), 'Соусы')]");
    private static final By FILLING_TAB_ACTIVE = By.xpath("//div[contains(@class, 'tab_type_current')]//span[contains(text(), 'Начинки')]");

    @Step("Клик на вкладку 'Булки'")
    public void clickBunsTab() {
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        click(BUNS_TAB);
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Клик на вкладку 'Соусы'")
    public void clickSaucesTab() {
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        click(SAUCES_TAB);
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Клик на вкладку 'Начинки'")
    public void clickFillingTab() {
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        click(FILLING_TAB);
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Проверка: вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        try {
            Thread.sleep(500);
            return isElementDisplayed(BUNS_TAB_ACTIVE);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка: вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        try {
            Thread.sleep(500);
            return isElementDisplayed(SAUCES_TAB_ACTIVE);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка: вкладка 'Начинки' активна")
    public boolean isFillingTabActive() {
        try {
            Thread.sleep(500);
            return isElementDisplayed(FILLING_TAB_ACTIVE);
        } catch (Exception e) {
            return false;
        }
    }
}
