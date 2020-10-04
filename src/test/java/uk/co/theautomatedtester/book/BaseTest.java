package uk.co.theautomatedtester.book;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    WebDriver webDriver;
    String url = "http://book.theautomatedtester.co.uk/";

    @BeforeClass
    public void setUp() {
        String driverPath = "C:\\Users\\Priyakshi\\MyWorkspace\\selenium-learning-tb-book-example-2020\\src\\main\\resources\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(url);
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
