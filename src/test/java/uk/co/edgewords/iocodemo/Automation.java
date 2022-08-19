package uk.co.edgewords.iocodemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Automation {
    public static void main(String[] args) {
        //Fetch ChromeDriver
        WebDriverManager.chromedriver().setup();
        //Open Chrome
        WebDriver driver = new ChromeDriver();

        //Implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Navigate to a page
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/");
        //Click a link
        driver.findElement(By.linkText("Login To Restricted Area")).click();

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
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement logoutLink = mywait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
        mywait.until(drv -> drv.findElement(By.linkText("Log Out")).isDisplayed());


        //Do Logout
        driver.findElement(By.linkText("Log Out")).click();
        //logoutLink.click();


        //Cleanup - close browser
        //driver.close(); //Closes the current tab - which if it is the only tab would also close the window
        driver.quit(); //Close the window and all tabs (if any)

    }
}
