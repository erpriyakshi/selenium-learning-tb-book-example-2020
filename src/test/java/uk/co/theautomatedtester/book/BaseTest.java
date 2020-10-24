package uk.co.theautomatedtester.book;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    WebDriver webDriver;

    @BeforeClass
    public void setUp() {
        String driverPath = "C:\\Users\\Priyakshi\\MyWorkspace\\selenium-learning-tb-book-example-2020\\src\\main\\resources\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
