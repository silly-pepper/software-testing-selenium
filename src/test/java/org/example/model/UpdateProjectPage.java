package org.example.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class UpdateProjectPage {

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[1]/div[2]/div/div/form/div/div[1]/div[1]/div/input")
    WebElement title;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[1]/div[2]/div/div/form/div/div[2]/button")
    WebElement saveButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div[1]/div[2]/div/div/div[1]")
    WebElement successMessage;


    public UpdateProjectPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
