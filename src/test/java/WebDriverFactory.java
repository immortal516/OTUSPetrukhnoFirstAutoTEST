import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static WebDriver create(String webDriverName) {
        if (webDriverName == null || webDriverName.isBlank()) {
            throw new IllegalArgumentException("webDriverName is required");
        }

        BrowserName browser = BrowserName.valueOf(webDriverName.trim().toUpperCase());

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            default:
                throw new IllegalArgumentException("Unsupported browser: " + webDriverName);
        }
    }

    public static WebDriver create(String webDriverName, Object options) {
        if (webDriverName == null || webDriverName.isBlank()) {
            throw new IllegalArgumentException("webDriverName is required");
        }

        BrowserName browser = BrowserName.valueOf(webDriverName.trim().toUpperCase());

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = options instanceof ChromeOptions ? (ChromeOptions) options : new ChromeOptions();
                return new ChromeDriver(chromeOptions);

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = options instanceof FirefoxOptions ? (FirefoxOptions) options : new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);

            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = options instanceof EdgeOptions ? (EdgeOptions) options : new EdgeOptions();
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + webDriverName);
        }
    }

    public static WebDriver createNewDriver(String webDriverName) {
        return create(webDriverName);
    }

    public static WebDriver createNewDriver(String webDriverName, Object options) {
        return create(webDriverName, options);
    }
}