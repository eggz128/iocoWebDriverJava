package uk.co.edgewords.iocodemo.POMPages;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.hamcrest.CoreMatchers.is;

public class LoginPagePOM {
    //Field to hold a webdriver (taken from the calling test)
    WebDriver driver;

    //Constructor to get the driver, and call the page factory class with "this" class
    public LoginPagePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        MatcherAssert.assertThat(driver.getTitle(), is("Automated Tools Test Site"));
    }

    //Locators
    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(linkText = "Submit")
    public WebElement submitButton;

    //Service Method
    public void setUserName(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitLoginForm(){
        submitButton.click();
    }

    //Combined helper methods
    public void doLogin(String username, String password){
        setUserName(username);
        setPassword(password);
        submitLoginForm();
    }

    public boolean doLoginExpectSuccess(String username, String password){
        doLogin(username, password);
        if (loggedin()==true){
            return true;
        }else{
            return false;
        }
    }

    public boolean doLoginExpectFail(String username, String password){
        doLogin(username, password);
        if (loggedin()==false){
            return false;
        }else{
            return true;
        }
    }

    public boolean loggedin(){

        boolean weAreLoggedIn = false;

        try{
            driver.switchTo().alert();
            weAreLoggedIn = false;
        } catch (Exception e){
            weAreLoggedIn = true;
        }

        return weAreLoggedIn;

    }
}
