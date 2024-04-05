package org.example.model;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProjectPage {

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[1]/header/ul/li[1]/span[2]/strong")
    WebElement sidebarName;

    public ProjectPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}


