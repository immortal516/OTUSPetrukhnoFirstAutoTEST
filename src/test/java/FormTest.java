import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class FormTest extends WebDriverBaseTest {

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        startChrome(options);
        driver.manage().window().setSize(new Dimension(1280, 900));
    }

    @Test
    void shouldFillFormAndSubmit() {
        String login = System.getProperty("login");
        String password = System.getProperty("password");

        assertNotNull(login, "Нужно передать -Dlogin=...");
        assertNotNull(password, "Нужно передать -Dpassword=...");

        String email = login.contains("@") ? login : "user_" + login + "@example.com";
        String birthDate = "1991-02-10";
        String languageValue = "intermediate";
        String baseUrl = System.getProperty("baseUrl", "https://otus.home.kartushin.su");

        log.info("Open form page");
        driver.get(baseUrl + "/form.html");

        driver.findElement(By.id("username")).sendKeys(login);
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirm_password")).sendKeys(password);

        assertEquals(
                driver.findElement(By.id("password")).getAttribute("value"),
                driver.findElement(By.id("confirm_password")).getAttribute("value"),
                "Пароль и подтверждение пароля должны совпадать"
        );

        WebElement birthdateInput = driver.findElement(By.id("birthdate"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                birthdateInput, birthDate
        );

        new Select(driver.findElement(By.id("language_level"))).selectByValue(languageValue);

        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String text = driver.findElement(By.id("output")).getText();

        assertAll(
                () -> assertTrue(text.contains(login), "Имя не найдено в output"),
                () -> assertTrue(text.contains(email), "Email не найден в output"),
                () -> assertTrue(text.contains(birthDate), "Дата рождения не найдена в output"),
                () -> assertTrue(text.contains(languageValue), "Уровень языка (value) не найден в output")
        );

        log.info("Test passed");
    }
}