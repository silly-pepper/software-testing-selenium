package org.example.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class SignUpPage {

    @FindBy(xpath = "//input[@name = 'fullname']")
    WebElement name;

    @FindBy(xpath = "//input[@name = 'email']")
    WebElement email;

    @FindBy(xpath = "//input[@name = 'new_username']")
    WebElement username;

    @FindBy(xpath = "//input[@name = 'new_password']")
    WebElement password;

    @FindBy(xpath = "/html/body/div[1]/div/div/form/div[3]/input")
    WebElement acceptButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/form/input[6]")
    WebElement signUpButton;

    @FindBy(xpath = "//input[@name = 'website_domain']")
    WebElement webDomain;

    @FindBy(xpath = "//input[@name = 'projectname']")
    WebElement projName;

    @FindBy(xpath = "//input[@name = 'addmyproject']")
    WebElement addProjectButton;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
