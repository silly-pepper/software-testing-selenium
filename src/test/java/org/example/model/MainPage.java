package org.example.model;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class MainPage {

    @FindBy(xpath = "//div[@class = 'col col-span-9 col-nav']/nav/ul/li/a[@href = '/login/']")
    WebElement loginHref;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
