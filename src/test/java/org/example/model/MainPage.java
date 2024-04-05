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

    @FindBy(xpath = "//div[@class = 'col col-span-9 col-nav']/nav/ul/li/a[@href = '/features/']")
    WebElement featuresHref;

    @FindBy(xpath = "//div[@class = 'col col-span-9 col-nav']/nav/ul/li/a[@href = '/pricing/']")
    WebElement pricingHref;

    @FindBy(xpath = "//div[@class = 'col col-span-9 col-nav']/nav/ul/li/a[@href = '/sign-up/']")
    WebElement signupHref;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
