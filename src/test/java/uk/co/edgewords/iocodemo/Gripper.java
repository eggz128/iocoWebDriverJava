package uk.co.edgewords.iocodemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


public class Gripper {
    public static void main(String[] args) {
        //Fetch ChromeDriver
        WebDriverManager.firefoxdriver().setup(); //Get geckodriver instead of chrome
        //WebDriverManager.chromedriver().setup();

        //Open a browser
        //WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();

        //Navigate to a page
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/docs/cssXPath.html");

        //Get gripper
        WebElement gripper = driver.findElement(By.cssSelector(".ui-slider-handle"));

        //Complex interaction
        Actions myAction = new Actions(driver); //Pass the driver(/browser/chrome) to Actions so it is able to perform actions
        Action dragDrop = myAction.clickAndHold(gripper) //Create an individual action chain. Get the gripper from earlier
                .moveByOffset(10,0) //Lots of little steps so the apple resizes in Chrome
                .moveByOffset(10,0) //Not needed for Firefox, but doesn't hurt it either
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .moveByOffset(10,0)
                .release() //Release the mouse button after dragging
                .build(); //build the chain ready for use
        dragDrop.perform(); //perform (do) the action
    }
}
