package org.example;

import org.example.model.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

public class MenuTest {
    private MainPage mainPage;
    private WebDriverWait wait;

    public void setUpDriver(WebDriver driver) {
        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void loginHrefTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getLoginHref().click();
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "login/"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void featuresHrefTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getFeaturesHref().click();
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "features/"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void pricingHrefTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getPricingHref().click();
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "pricing/"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void signupHrefTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getSignupHref().click();
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "sign-up/"));
        driver.quit();
    }

    public static Stream<Object[]> driverProvider() {
        WebDriver cDriver = new ChromeDriver();
        cDriver.manage().window().maximize();
        cDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        cDriver.get(Utils.BASE_URL);

        WebDriver fDriver = new FirefoxDriver();
        fDriver.manage().window().maximize();
        fDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        fDriver.get(Utils.BASE_URL);

        return Stream.of(
                new Object[] {cDriver},
                new Object[] {fDriver}
        );
    }
}
