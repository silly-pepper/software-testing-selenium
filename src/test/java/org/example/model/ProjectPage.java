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

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[3]/div[2]/a[1]")
    WebElement addProjectButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[4]/table/tbody/tr/td[8]/a")
    WebElement viewStatsButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[4]/table/tbody/tr/td[7]/div[1]")
    WebElement settingsButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[4]/table/tbody/tr/td[7]/div[2]/div/ul/li[1]/a")
    WebElement editProjectButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[4]/table/tbody/tr/td[7]/div[2]/div/ul/li[6]/a")
    WebElement deleteProjectButton;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[1]/p/a[2]")
    WebElement undoDeletion;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[2]/div/div/div[4]/table/tbody/tr")
    WebElement project;

    @FindBy(xpath = "/html/body/div[4]/div[3]/div/div[3]/div/div/form/div/div[15]/span")
    WebElement choseBasicPlanButton;

    public ProjectPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}


