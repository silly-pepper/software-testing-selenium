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

    @FindBy(xpath = "//input[@name = 'terms']")
    WebElement acceptButton;

    @FindBy(xpath = "//input[@type = 'submit' and @value = 'Create Account']")
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
