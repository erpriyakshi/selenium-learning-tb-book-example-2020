package uk.co.theautomatedtester.book;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter1Test extends BaseTest {
    //Verify Description and heading on Chapter1 Page
    @Test
    public void testDescriptionsAndHeading() {
        //Given
        String expectedHeading = "Selenium: Beginners Guide";
        String expectedDescription = "If you have arrived here then you have installed Selenium IDE and are ready to start recording your first test.";
        //When
        webDriver.findElement(By.xpath("(//div[@class='mainbody']//ul//a)[1]")).click();
        WebElement mainHeadingElement = webDriver.findElement(By.className("mainheading"));
        String actualMainHeading = mainHeadingElement.getText();
        WebElement descriptionElement = webDriver.findElement(By.xpath("(//div[@class='mainbody']//p)[1]"));
        String actualDescription = descriptionElement.getText();
        //then
        Assert.assertEquals(actualMainHeading, expectedHeading);
        Assert.assertEquals(actualDescription, expectedDescription);
    }
}
