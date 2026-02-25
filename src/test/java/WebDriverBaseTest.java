import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public abstract class WebDriverBaseTest {
    protected WebDriver driver;
    protected final Logger log = LogManager.getLogger(getClass());

    @BeforeEach
    void startDriver() {
        String browser = System.getProperty("browser", "chrome");
        try {
            driver = WebDriverFactory.create(browser);
        } catch (RuntimeException e) {
            Assumptions.abort("Browser '" + browser + "' is not available in current environment: " + e.getMessage());
        }
        driver.manage().window().setSize(new Dimension(1280, 900));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}