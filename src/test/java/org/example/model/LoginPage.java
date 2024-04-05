package org.example.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {

    @FindBy(xpath = "//input[@name = 'form_user']")
    WebElement username;

    @FindBy(xpath = "//input[@name = 'form_pass']")
    WebElement password;

    @FindBy(xpath = "//input[@class = 'submit']")
    WebElement loginButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/h2")
    WebElement wrongLoginDiv;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
