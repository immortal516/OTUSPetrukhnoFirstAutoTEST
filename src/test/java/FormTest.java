import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static org.junit.jupiter.api.Assertions.*;

public class FormTest extends WebDriverBaseTest {

    @Test
    void shouldFillFormAndSubmit() {
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String baseUrl = System.getProperty("baseUrl", "https://otus.home.kartushin.su");

        assertNotNull(login, "Нужно передать -Dlogin=...");
        assertNotNull(password, "Нужно передать -Dpassword=...");

        String email = login.contains("@") ? login : "user_" + login + "@example.com";
        String birthDate = "1991-02-10";
        String languageValue = "intermediate";

        log.info("Open page");
        RegistrationFormPage page = new RegistrationFormPage(driver)
                .open(baseUrl)
                .setUsername(login)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(password)
                .setBirthdate(birthDate)
                .setLanguageLevelByValue(languageValue);

        assertEquals(page.getPasswordValue(), page.getConfirmPasswordValue(), "Пароли должны совпадать");

        page.submit();

        String out = page.outputText();

        assertAll(
                () -> assertTrue(out.contains(login)),
                () -> assertTrue(out.contains(email)),
                () -> assertTrue(out.contains(birthDate)),
                () -> assertTrue(out.contains(languageValue))
        );

        log.info("Test passed");
    }
}