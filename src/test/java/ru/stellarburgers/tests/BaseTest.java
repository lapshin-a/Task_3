package ru.stellarburgers.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.stellarburgers.api.UserApi;
import io.restassured.response.Response;

public class BaseTest {
    protected WebDriver driver;
    protected String userEmail;
    protected String userPassword;
    protected String userName;
    protected String accessToken;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if ("yandex".equals(browser)) {
            setupYandexDriver();
        } else {
            setupChromeDriver();
        }

        driver.manage().window().maximize();
        
        // Создание пользователя через API перед каждым тестом
        userEmail = UserApi.generateEmail();
        userPassword = UserApi.generatePassword();
        userName = UserApi.generateName();
        
        Response registerResponse = UserApi.registerUser(userEmail, userPassword, userName);
        registerResponse.then().statusCode(200);
        
        // Сохраняем токен для удаления в конце теста
        accessToken = registerResponse.jsonPath().getString("accessToken");
    }

    private void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    private void setupYandexDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        
        // Удаление созданного пользователя через API, если токен доступен
        if (accessToken != null && !accessToken.isEmpty()) {
            UserApi.deleteUser(accessToken);
        }
    }

    protected void clearSession() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }
}
