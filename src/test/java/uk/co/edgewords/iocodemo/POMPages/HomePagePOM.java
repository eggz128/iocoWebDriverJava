package uk.co.edgewords.iocodemo.POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePOM {
    //Field to hold a webdriver (taken from the calling test)
    WebDriver driver;

    //Constructor to get the driver, and call the page factory class with "this" class
    public HomePagePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(linkText = "Login To Restricted Area")
    WebElement loginLink;

    //Service Methods
    public void GoToLogin(){
        loginLink.click();
    }
}
