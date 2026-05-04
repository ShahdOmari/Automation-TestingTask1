package pages;

import com.generic.selector.LoginSelectors;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    private JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    private void scrollToElement(WebElement element) {
        js().executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    private void jsSetValue(WebElement element, String value) {
        js().executeScript(
            "var el = arguments[0];" +
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(" +
            "    window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(el, arguments[1]);" +
            "el.dispatchEvent(new Event('input', { bubbles: true }));" +
            "el.dispatchEvent(new Event('change', { bubbles: true }));",
            element, value
        );
    }


    public void dismissCookieBannerIfPresent() {
        try {
            WebElement cookie = new WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                    by(LoginSelectors.cookieBannerCloseBtn)));
            scrollToElement(cookie);
            cookie.click();
            System.out.println("[LoginPage] Cookie banner closed!");
            new WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(cookie));
        } catch (Exception e) {
            System.out.println("[LoginPage] No cookie banner: " + e.getMessage());
        }
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(
            ExpectedConditions.presenceOfElementLocated(by(LoginSelectors.emailField))
        );
        scrollToElement(field);
        jsSetValue(field, email);
        System.out.println("[LoginPage] Email entered: " + email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(
            ExpectedConditions.presenceOfElementLocated(by(LoginSelectors.passwordField))
        );
        scrollToElement(field);
        jsSetValue(field, password);
        System.out.println("[LoginPage] Password entered!");
    }

    public void clickLogin() {
        WebElement button = wait.until(
            ExpectedConditions.presenceOfElementLocated(by(LoginSelectors.loginButton))
        );
        scrollToElement(button);
        wait.until(ExpectedConditions.elementToBeClickable(by(LoginSelectors.loginButton)));
        js().executeScript("arguments[0].click();", button);
        System.out.println("[LoginPage] Login button clicked!");
    }

    public void login(String email, String password) {
        dismissCookieBannerIfPresent();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}
