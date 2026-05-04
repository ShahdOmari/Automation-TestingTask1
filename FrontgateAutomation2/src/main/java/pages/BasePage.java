package pages;

import com.generic.setup.cselector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * RULE — POM: every page class extends BasePage.
 * Original BasePage kept intact + added by() helper to resolve cselector → By.
 */
public class BasePage {

    protected WebDriver     driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Converts a cselector into a Selenium By.
     * RULE — Prefer id > name > CSS. No XPath generated.
     */
    protected By by(cselector sel) {
        String[] parts = sel.primary.split(",", 2);
        switch (parts[0].trim().toLowerCase()) {
            case "id":   return By.id(parts[1].trim());
            case "name": return By.name(parts[1].trim());
            default:     return By.cssSelector(parts[1].trim());
        }
    }
}
