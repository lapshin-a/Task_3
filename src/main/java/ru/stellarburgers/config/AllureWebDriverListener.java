package ru.stellarburgers.config;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.ByteArrayInputStream;

public class AllureWebDriverListener implements WebDriverListener {

    public void afterSwitchToWindow(WebDriver driver, String handle) {
        takeScreenshot(driver);
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        takeScreenshot(driver);
    }

    public void afterClick(WebDriver driver) {
        takeScreenshot(driver);
    }

    private void takeScreenshot(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] screenshotAs = screenshot.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot",
                    new ByteArrayInputStream(screenshotAs)
            );
        }
    }
}
