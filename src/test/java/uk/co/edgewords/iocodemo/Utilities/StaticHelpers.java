package uk.co.edgewords.iocodemo.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class StaticHelpers {

    public static void WaitForElementToBeDisplayedStatic(WebDriver driver, int TimeOutSeconds, By locator){
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(TimeOutSeconds));
        mywait.until(drv -> drv.findElement(locator).isDisplayed());
    }

    public static void TakeScreenshot(WebDriver driver, String fileName) {

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(screenshot, new File("D:\\screenshots\\" + fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to write screenshot:" + fileName);
        }
    }

    public static void TakeScreenshotOfElement(WebElement elm, String fileName) {

        File screenshot = elm.getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(screenshot, new File("D:\\screenshots\\" + fileName + ".png"));
        } catch (IOException e) {
            System.out.println("FAiled to write screenshot:" + fileName);
        }
    }

}
