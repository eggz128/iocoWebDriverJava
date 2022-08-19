package uk.co.edgewords.iocodemo.Tests;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.*;
import static uk.co.edgewords.iocodemo.Utilities.StaticHelpers.TakeScreenshot;
import static uk.co.edgewords.iocodemo.Utilities.StaticHelpers.TakeScreenshotOfElement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import uk.co.edgewords.iocodemo.POMPages.HomePagePOM;
import uk.co.edgewords.iocodemo.POMPages.LoginPagePOM;
import uk.co.edgewords.iocodemo.Utilities.BaseTest;

public class LoginWithPOMTest extends BaseTest {

    @DisplayName("Reads from a CSV File")
    @ParameterizedTest(name = "{displayName}{index} => username={0}, password={1}")
    @CsvFileSource(files = "test-data.csv", useHeadersInDisplayName = true)
    void Login(String username, String password){
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/");

        //Now instantiate POM page and use it
        HomePagePOM home = new HomePagePOM(driver);
        home.GoToLogin();
        LoginPagePOM login = new LoginPagePOM(driver);
//        login.setUserName("edgewords");
//        login.setPassword("edgewords123");
//        login.submitLoginForm();
        //login.doLogin("edgewords","edgewords123");
        //login.usernameField.findElement(By.xpath("./../../.."))

        //Check if we are logged in
        //MatcherAssert.assertThat("Alert present - login must have failed",login.loggedin(), is(true));

        WebElement form = driver.findElement(By.id("Login"));
        var formsize = form.getRect();
        System.out.println(formsize.height);
        System.out.println(formsize.x);
        TakeScreenshotOfElement(form, "justtheform");

       boolean loggedin = login.doLoginExpectSuccess(username,password);
       TakeScreenshot(driver,"myscreenshot");



       MatcherAssert.assertThat("Not logged in",loggedin==true);
    }

    @DisplayName("This name is from the annotation")
    @Test
    void LoginWithInvalidData(){
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/");

        //Now instantiate POM page and use it
        HomePagePOM home = new HomePagePOM(driver);
        home.GoToLogin();
        LoginPagePOM login = new LoginPagePOM(driver);
        boolean loggedin = login.doLoginExpectFail("edgewords","edgewords1234");
        MatcherAssert.assertThat("We Logged in unexpectedly",loggedin==true);
    }

}
