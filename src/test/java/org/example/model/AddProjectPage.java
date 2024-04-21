package org.example.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AddProjectPage {

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[1]/div[2]/div/div/form/div/div/div/div[1]/div/input[1]")
    WebElement url;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[1]/div[2]/div/div/form/div/div/div/div[1]/div/input[2]")
    WebElement title;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[1]/div[2]/div/div/form/div/div/div/input")
    WebElement addProjectButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[3]/div/div/div/div/div/div/div[2]/a")
    WebElement defaultInstallationButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div/div[1]/div/div[1]/form/div/a")
    WebElement verifyInstallationButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div/div[1]/div/div[1]/div[2]/form/a")
    WebElement skipVerificationButton;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div/form/button")
    WebElement letsGetStartedButton;

    public AddProjectPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
