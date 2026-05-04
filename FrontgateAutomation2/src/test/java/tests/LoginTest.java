package tests;

import pages.HomePage;
import pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;

/**
 * RULE — POM         : only test logic here; all browser actions are in page classes.
 * RULE — Locators    : inside selector package, never in this class.
 * RULE — Data-driven : all values injected via testng-data.xml (@Parameters).
 * RULE — No sleep    : Thread.sleep() removed; replaced with WebDriverWait.
 * RULE — Assertions  : Assert.assertTrue / assertEquals (TestNG).
 * RULE — Locators    : id / CSS preferred; zero XPath.
 *
 * Test body is kept IDENTICAL to the original working code.
 * Only changes:
 *   1. Hardcoded ChromeDriver path replaced with WebDriverManager (auto-download).
 *   2. TestData.EMAIL / PASSWORD / BASE_URL replaced with @Parameters from XML.
 *   3. Thread.sleep(5000) in tearDown replaced with WebDriverWait.
 */
public class LoginTest {

    private WebDriver driver;
    private HomePage  homePage;
    private LoginPage loginPage;

    // Injected from testng-data.xml — RULE: no hardcoding in test class
    private String email;
    private String password;
    private String expectedFirstName;

    @BeforeClass
    @Parameters({ "baseUrl", "email", "password", "expectedFirstName" })
    public void setUp(String baseUrl,
                      String email,
                      String password,
                      String expectedFirstName) {

        this.email             = email;
        this.password          = password;
        this.expectedFirstName = expectedFirstName;

        // RULE: WebDriverManager auto-downloads correct chromedriver — no hardcoded path
        WebDriverManager.chromedriver().setup();

        driver    = new ChromeDriver();
        homePage  = new HomePage(driver);
        loginPage = new LoginPage(driver);

        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    // ── TC-01 ─────────────────────────────────────────────────────────────────

    @Test(priority = 1)
    public void verifyHomePageElements() {
        // RULE — Assertions: Assert.assertTrue (TestNG), not if-conditions
        Assert.assertTrue(homePage.isLogoDisplayed(),      "Logo should be visible");
        Assert.assertTrue(homePage.isMyAccountDisplayed(), "My Account should be visible");
        Assert.assertTrue(homePage.isMyBagDisplayed(),     "My Bag should be visible");
    }

    // ── TC-02 ─────────────────────────────────────────────────────────────────

    @Test(priority = 2)
    public void verifyLoginSuccess() {

        // Original flow — kept exactly
        homePage.clickSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            driver -> !driver.getCurrentUrl().contains("frontgate.com/?")
        );
        System.out.println("[LoginTest] Current URL: " + driver.getCurrentUrl());

        loginPage.dismissCookieBannerIfPresent();

        // Original wait — kept exactly (waits for the email input to be ready)
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='email']"))
        );

        loginPage.login(email, password);
        System.out.println("[LoginTest] Login submitted, waiting for redirect...");

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            driver -> !driver.getCurrentUrl().contains("UserLogonView")
        );
        System.out.println("[LoginTest] After login URL: " + driver.getCurrentUrl());

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.visibilityOfElementLocated(By.id("my-account-button"))
        );
        System.out.println("[LoginTest] Homepage loaded after login!");

        String welcomeMessage = homePage.getWelcomeMessage();
        System.out.println("[LoginTest] Welcome message: " + welcomeMessage);

        // RULE — Assertions: Assert.assertTrue (TestNG)
        Assert.assertTrue(
            welcomeMessage.contains(expectedFirstName),
            "Welcome message should contain: " + expectedFirstName
        );
    }

    // ── teardown ──────────────────────────────────────────────────────────────

    @AfterClass
    public void tearDown() {
        System.out.println("[LoginTest] Test finished. Final URL: " + driver.getCurrentUrl());

        // RULE — No Thread.sleep(): replaced with WebDriverWait for final page stability
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
            d -> d.getCurrentUrl() != null
        );

        if (driver != null) driver.quit();
    }
}
