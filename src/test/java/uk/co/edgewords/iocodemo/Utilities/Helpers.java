package uk.co.edgewords.iocodemo.Utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helpers {

    //Field to hold our WebDriver
    WebDriver driver;

    public Helpers(WebDriver driver) {
        this.driver = driver;
    }

    public void WaitForElementToBeDisplayed(int TimeOutSeconds, By locator){
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(TimeOutSeconds));
        mywait.until(drv -> drv.findElement(locator).isDisplayed());
    }

    public void HandleAlert(){
        Alert myAlert = driver.switchTo().alert();
        String alertText = myAlert.getText();
        System.out.println("The alert text is: " + alertText);
        myAlert.accept();
    }
}
