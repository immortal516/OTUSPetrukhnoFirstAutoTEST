package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class RegistrationFormPage {
    private final WebDriver driver;

    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationFormPage open(String baseUrl) {
        driver.get(baseUrl + "/form.html");
        return this;
    }

    public RegistrationFormPage setUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        return this;
    }

    public RegistrationFormPage setPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
        return this;
    }

    public RegistrationFormPage setConfirmPassword(String password) {
        driver.findElement(By.id("confirm_password")).sendKeys(password);
        return this;
    }

    public String getPasswordValue() {
        return driver.findElement(By.id("password")).getAttribute("value");
    }

    public String getConfirmPasswordValue() {
        return driver.findElement(By.id("confirm_password")).getAttribute("value");
    }

    public RegistrationFormPage setBirthdate(String yyyyMmDd) {
        WebElement birthdateInput = driver.findElement(By.id("birthdate"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                birthdateInput, yyyyMmDd
        );
        return this;
    }

    public RegistrationFormPage setLanguageLevelByValue(String value) {
        new Select(driver.findElement(By.id("language_level"))).selectByValue(value);
        return this;
    }

    public RegistrationFormPage submit() {
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        return this;
    }

    public String outputText() {
        return driver.findElement(By.id("output")).getText();
    }
}