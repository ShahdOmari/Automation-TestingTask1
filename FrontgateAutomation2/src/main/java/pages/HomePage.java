package pages;

import com.generic.selector.HomePageSelectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                by(HomePageSelectors.logo))
        ).isDisplayed();
    }

    public boolean isMyAccountDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                by(HomePageSelectors.myAccountButton))
        ).isDisplayed();
    }

    public boolean isMyBagDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                by(HomePageSelectors.myBagButton))
        ).isDisplayed();
    }

    public void clickSignIn() {
        WebElement accountBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                by(HomePageSelectors.myAccountButton))
        );
        new Actions(driver).moveToElement(accountBtn).perform();
        wait.until(
            ExpectedConditions.elementToBeClickable(
                by(HomePageSelectors.signInLink))
        ).click();
    }

    public String getWelcomeMessage() {
        WebElement accountBtn = new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(
                by(HomePageSelectors.myAccountButton)));

        new Actions(driver).moveToElement(accountBtn).perform();

        return new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(
                by(HomePageSelectors.welcomeMessage)))
            .getText();
    }
}
