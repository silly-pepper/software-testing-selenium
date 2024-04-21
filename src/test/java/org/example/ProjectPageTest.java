package org.example;

import org.example.model.*;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectPageTest {

    private ProjectPage projectPage;
    private AddProjectPage addProjectPage;
    private UpdateProjectPage updateProjectPage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private WebDriverWait wait;

    private void setUpDriver(WebDriver driver) {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        addProjectPage = new AddProjectPage(driver);
        updateProjectPage = new UpdateProjectPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void login() {
        mainPage.getLoginHref().click();
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "login/"));
        loginPage.getUsername().sendKeys(Utils.CORRECT_LOGIN);
        loginPage.getPassword().sendKeys(Utils.CORRECT_PASSWORD);
        loginPage.getLoginButton().click();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void addProjectTest(WebDriver driver) {
        setUpDriver(driver);
        login();

        projectPage.getAddProjectButton().click();
        wait.until(ExpectedConditions.urlToBe("https://statcounter.com/choose-a-plan.php"));

        projectPage.getChoseBasicPlanButton().click();
        wait.until(ExpectedConditions.urlToBe("https://statcounter.com/add-project/?plan=basic"));

        addProjectPage.getUrl().sendKeys("https://statcounter.com/add-project/?plan=basic");
        addProjectPage.getTitle().sendKeys("aboba");
        addProjectPage.getAddProjectButton().click();
        wait.until(ExpectedConditions.urlContains("https://statcounter.com/snippet/?project_id="));

        addProjectPage.getDefaultInstallationButton().click();
        wait.until(ExpectedConditions.urlContains("https://statcounter.com/p"));

        addProjectPage.getVerifyInstallationButton().click();
        addProjectPage.getSkipVerificationButton().click();
        addProjectPage.getLetsGetStartedButton().click();

        wait.until(ExpectedConditions.urlToBe("https://statcounter.com/"));
        assertEquals("aboba", projectPage.getFirstProjectName().getText());
        projectPage.getSettingsButton().click();
        projectPage.getDeleteProjectButton().click();

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void choseProjectTest(WebDriver driver) {
        setUpDriver(driver);
        login();

        projectPage.getSettingsButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[3]/div/div[2]/div/div/div[4]/table/tbody/tr/td[7]/div[2]")));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void updateProjectTest(WebDriver driver) {
        setUpDriver(driver);
        login();

        projectPage.getSettingsButton().click();
        projectPage.getEditProjectButton().click();
        wait.until(ExpectedConditions.urlContains("/settings/"));

        updateProjectPage.getTitle().clear();
        updateProjectPage.getTitle().sendKeys("aboba2");
        updateProjectPage.getSaveButton().click();

        wait.until(ExpectedConditions.visibilityOf(updateProjectPage.getSuccessMessage()));

        updateProjectPage.getTitle().clear();
        updateProjectPage.getTitle().sendKeys("aboba");
        updateProjectPage.getSaveButton().click();

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void viewProjectStatsTest(WebDriver driver) {
        setUpDriver(driver);
        login();

        projectPage.getViewStatsButton().click();
        wait.until(ExpectedConditions.urlContains("/summary/"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void deleteProjectTest(WebDriver driver) {
        setUpDriver(driver);
        login();

        projectPage.getSettingsButton().click();
        projectPage.getDeleteProjectButton().click();
        wait.until(ExpectedConditions.invisibilityOf(projectPage.getFirstProject()));

        projectPage.getUndoDeletion().click();
        wait.until(ExpectedConditions.visibilityOf(projectPage.getFirstProject()));
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
