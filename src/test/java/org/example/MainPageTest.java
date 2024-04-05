package org.example;

import org.example.model.LoginPage;
import org.example.model.MainPage;
import org.example.model.SignUpPage;
import org.example.model.ProjectPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class MainPageTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private ProjectPage projectPage;

    public void setUpDriver(WebDriver driver) {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void loginTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getLoginHref().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "login/"));
        loginPage.getUsername().sendKeys(Utils.CORRECT_LOGIN);
        loginPage.getPassword().sendKeys(Utils.CORRECT_PASSWORD);
        loginPage.getLoginButton().click();
        String expectedUrl= driver.getCurrentUrl();
        String actualUrl= "https://statcounter.com/";
        assertEquals(actualUrl, expectedUrl);
        assertEquals(Utils.CORRECT_LOGIN, projectPage.getSidebarName().getText());
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void signUpTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getSignupHref().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "sign-up/"));

        signUpPage.getName().sendKeys(Utils.CORRECT_LOGIN);
        signUpPage.getEmail().sendKeys(Utils.WRONG_EMAIL);
        signUpPage.getUsername().sendKeys(Utils.CORRECT_LOGIN);
        signUpPage.getPassword().sendKeys(Utils.CORRECT_PASSWORD);
        signUpPage.getAcceptButton().click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class = 'error']")));

        signUpPage.getEmail().clear();
        signUpPage.getEmail().sendKeys(Utils.CORRECT_EMAIL);
        wait.until(ExpectedConditions.elementToBeClickable(signUpPage.getSignUpButton()));
        signUpPage.getSignUpButton().submit();

        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "sign-up/project/"));

        signUpPage.getProjName().sendKeys("ABOBA");
        signUpPage.getAddProjectButton().click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class = 'error']")));

        signUpPage.getWebDomain().sendKeys("ABOBA");
        signUpPage.getAddProjectButton().submit();

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void wrongLoginTest(WebDriver driver){
        setUpDriver(driver);
        mainPage.getLoginHref().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "login/"));

        loginPage.getUsername().sendKeys(Utils.INCORRECT_LOGIN);
        loginPage.getPassword().sendKeys(Utils.CORRECT_PASSWORD);
        loginPage.getLoginButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/h2")));
        System.out.println(loginPage.getWrongLoginDiv().getText());
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void wrongLoginWithNonLatinLettersTest(WebDriver driver){
        setUpDriver(driver);
        mainPage.getLoginHref().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "login/"));

        loginPage.getUsername().sendKeys(Utils.INCORRECT_RUSSIAN_LOGIN);
        loginPage.getPassword().sendKeys(Utils.CORRECT_PASSWORD);
        loginPage.getLoginButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/h2")));
        System.out.println(loginPage.getWrongLoginDiv().getText());
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
