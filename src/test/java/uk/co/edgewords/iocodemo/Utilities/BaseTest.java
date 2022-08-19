package uk.co.edgewords.iocodemo.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    WebDriverManager wdm;
    public WebDriver driver;
    public StringBuffer verificationerrors = new StringBuffer(); //A field to collect verification errors

    @BeforeEach
    void SetUp(){
//        WebDriverManager.chromedriver().setup();
//        //System.setProperty("webdriver.chrome.driver","C:\\Users\\StevePowell\\Documents\\DriverServers\\chromedriver.exe");
//
//        //Passing options to ChromeDriver
//        ChromeOptions options = new ChromeOptions();
//        Map<String, String> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceName","Nexus 5");
//        options.setExperimentalOption("mobileEmulation", mobileEmulation);
//        //options.addArguments("headless");
//
//        driver = new ChromeDriver(options);
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless");
//        driver = new FirefoxDriver(options);

//        WebDriverManager.iedriver().arch32().setup();
//        driver = new InternetExplorerDriver();

        //Edge
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();


        //Safari-ish
//        wdm = WebDriverManager.safaridriver().browserInDocker();
//        driver = wdm.create();

        String browser = System.getProperty("browser");
        System.out.println("Browser set to: " + browser);
        if(browser==null){browser="";};
        switch (browser){
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "remotechrome":
                ChromeOptions choptions = new ChromeOptions();
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.122.43:4444/wd/hub"),choptions);
                } catch (MalformedURLException e) {
                    System.out.println("Failed to start remote connection");
                    throw new RuntimeException(e);
                }
                break;
            case "remotefirefox":
                FirefoxOptions fxoptions = new FirefoxOptions();
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.122.43:4444/wd/hub"),fxoptions);
                } catch (MalformedURLException e) {
                    System.out.println("Failed to start remote connection");
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Browser not set at command line so using ChromeDriver");
                driver = WebDriverManager.chromedriver().create();
                break;
        }


        driver.manage().window().maximize();
    }
    @AfterEach
    void TearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
        wdm.quit();
        //If a test has verification errors, fail the test
        String allverificationerrors = verificationerrors.toString(); //Convert the collected errors to a simple string
        if(!allverificationerrors.isEmpty()){ //If the string is not empty we have errors
            System.out.println("Verification errors encountered during run");
            System.out.println(allverificationerrors);
            Assertions.fail("Failed test with verification errors"); //Fails the previous test
        }
    }
}
