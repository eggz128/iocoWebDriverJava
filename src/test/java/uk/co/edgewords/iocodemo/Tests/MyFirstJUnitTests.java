package uk.co.edgewords.iocodemo.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.co.edgewords.iocodemo.Utilities.BaseTest;
import uk.co.edgewords.iocodemo.Utilities.Helpers;
import static uk.co.edgewords.iocodemo.Utilities.StaticHelpers.*;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyFirstJUnitTests extends BaseTest {



    @Test
    @Order(1)
    @Tag("RunThis")
    void FirstTest() throws InterruptedException {
        //Navigate to a page
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/");
        //Click a link
        driver.findElement(By.linkText("Login To Restricted Area")).click();

        //Check to see if we are already logged in
        String loginPageText = driver.findElement(By.tagName("body")).getText();

        //Check if we are already logged in - if so log the error and fail the test *at the end* - i.e. later
        try{
            MatcherAssert.assertThat("Already Logged in", loginPageText, containsString("User is not Logged in"));
        } catch (AssertionError e){
            //If the assertion failed, catch the error and add it to the verification errors object
            verificationerrors.append(e.toString());
        }




        //Capture heading text and write it out to the console
        String heading = driver.findElement(By.cssSelector("#right-column > h1:nth-child(1)")).getText(); //Gets inner text i.e. <tag>This text</tag>
        System.out.println(heading); //Write to console

        //Get page title
        String title = driver.getTitle(); //Gets the page title. getText() won't work as the text is not displayed on the page
        System.out.println(title);

        //Type in username
        driver.findElement(By.cssSelector("#username")).clear(); //Clear any text in the input text box
        //or simulate a real user doing the same
        // driver.findElement(By.cssSelector("#username")).sendKeys(Keys.CONTROL + "a");
        // driver.findElement(By.cssSelector("#username")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.cssSelector("#username")).sendKeys("edgewords"); //Type in username. If there is text in there already it would be added to.

        //Type in password
        WebElement passwordField = driver.findElement(By.cssSelector("#password")); //Capture the element first without doing any actions
        passwordField.clear(); //Then perform those actions on the captured element
        passwordField.sendKeys("edgewords123");

        //Get text inside text box
        String typedPassword = passwordField.getAttribute("value"); //<input> has no inner text. Instead, you must get the value attribute.
        System.out.println("The text is " + typedPassword);

        //Click Submit
        driver.findElement(By.linkText("Submit")).click(); //It turns out the Submit button is not a button, but instead a link

        //A little wait so we can see what has worked
        //Explicit Unconditional Wait (stuck waiting wasting time)
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            //Do nothing
//        }

        //Explicit Conditional (Wait for thing to happen then proceed) - Best practice
        //WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement logoutLink = mywait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
        //mywait.until(drv -> drv.findElement(By.linkText("Log Out")).isDisplayed());
        //Get the helper method
        Helpers help = new Helpers(driver); //INstantiated the helper class with the driver from here

        help.WaitForElementToBeDisplayed(5, By.linkText("Log Out"));


        //Do Logout
        driver.findElement(By.linkText("Log Out")).click();

        //Handle Alert(Confirm)
        //driver.switchTo().alert().accept();
        help.HandleAlert();

        //Wait for the final page to appear before capturing
//        Thread.sleep(8000);
//        WebDriverWait mywait2 = new WebDriverWait(driver, Duration.ofSeconds(12));
//        mywait2.until(drv -> drv.findElement(By.linkText("Register")).isDisplayed());
        //help.WaitForElementToBeDisplayed(10, By.linkText("Register"));
        WaitForElementToBeDisplayedStatic(driver, 10, By.linkText("Register"));

        //Get page text to check we are logged out at the end
        String bodyText = driver.findElement(By.tagName("body")).getText();

        System.out.println("Body text captured is: " +bodyText);
        //Assertions.assertTrue(bodyText.contains("User is not Logged in")); //Classic JUNit
        //Hamcrest - Constraint model
        MatcherAssert.assertThat("We are still logged in",bodyText, containsStringIgnoringCase("User is not logged in"));
    }




    @Test
    @Order(2)
    void SecondTest() throws InterruptedException {
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/");
        Thread.sleep(1000);

    }



}
