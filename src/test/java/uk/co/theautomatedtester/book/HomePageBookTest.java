package uk.co.theautomatedtester.book;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class HomePageBookTest extends BaseTest {

    //verify Title "Selenium: Beginners Guide" of Home Page
    @Test
    public void testTitle() {
        //given
        String expectedTitle = "Selenium: Beginners Guide";

        //when
        String actualTitle = webDriver.getTitle();

        //then
        assertEquals(actualTitle, expectedTitle);
    }

    //verify Page Heading
    @Test
    public void testPageHeading() {
        //given
        String expectedHeading = "Selenium: Beginners Guide";
        //when
        WebElement mainHeading = webDriver.findElement(By.className("mainheading"));
        String actualHeading = mainHeading.getText();
        //then
        assertEquals(actualHeading, expectedHeading);
    }


    //verify Descriptions on HomePage
    @Test
    public void testDescriptions() {
        //given
        String expectedDescriptions = "Below is a list of links to the examples needed in the chapters. Click on the links below and follow the steps in the book.";

        //when
        WebElement descriptionElement = webDriver.findElement(By.xpath("//div[@class='mainbody']"));
        String actualDescription = descriptionElement.getText();

        //then
        assertNotNull(actualDescription);
        assertTrue(actualDescription.startsWith(expectedDescriptions), "[" + actualDescription + "] does not contains [" + expectedDescriptions + "]");
    }

    //verify link on homepage
    @Test
    public void testLinks() {
        //given
        int expectedLinks = 5;
        ArrayList<String> expectedlinksName = new ArrayList<>();
        expectedlinksName.add("Chapter1");
        expectedlinksName.add("Chapter2");
        expectedlinksName.add("Chapter3");
        expectedlinksName.add("Chapter4");
        expectedlinksName.add("Chapter8");

        //when
        List<WebElement> chapterLinks = webDriver.findElements(By.xpath("//div[@class='mainbody']/ul/li"));
        int actualLinks = chapterLinks.size();
        ArrayList<String> actualLinksName = new ArrayList<>();
        for (int chapterLinkIndex = 0; chapterLinkIndex < chapterLinks.size(); chapterLinkIndex++) {
            WebElement chapterLink = chapterLinks.get(chapterLinkIndex);
            actualLinksName.add(chapterLink.getText());
        }
        //then
        assertEquals(expectedLinks, actualLinks);
        assertEquals(actualLinksName, expectedlinksName);
    }

    //verify input field on home page
    @Test
    public void testInputField() throws InterruptedException {
        //given
        //when
        WebElement inputField = webDriver.findElement(By.xpath("//div[@class='mainbody']/input"));
        String expectedText = "It is text input field";
        inputField.sendKeys(expectedText);
        boolean enabled = inputField.isEnabled();
        String actualText = inputField.getAttribute("value");
        //then
        assertTrue(enabled, "Input field is not enabled");
        assertEquals(actualText, expectedText);
    }

    //verify all links click one by one
    @Test
    public void testClicksAllLinks() {
        //given
        //when
        List<WebElement> chapterLinks = webDriver.findElements(By.xpath("//div[@class='mainbody']/ul/li/a"));
        for (int i = 1; i <= chapterLinks.size(); i++) {
            WebElement chapterLink = webDriver.findElement(By.xpath("(//div[@class='mainbody']/ul/li/a)[" + i + "]"));
            String expectedUrl = chapterLink.getAttribute("href");
            chapterLink.click();
            String actualUrl = webDriver.getCurrentUrl();
            //then
            assertEquals(actualUrl, expectedUrl);
            webDriver.navigate().back();
        }

    }

    @Test
    public void testClicksAllLinksOpenInNewWindow() throws InterruptedException {
        //given
        String parent = webDriver.getWindowHandle();
        //when
        List<WebElement> chapterLinks = webDriver.findElements(By.xpath("//div[@class='mainbody']/ul/li/a"));
        for (int chapterLinkXPathIndex = 1; chapterLinkXPathIndex <= chapterLinks.size(); chapterLinkXPathIndex++) {
            WebElement chapterLink = webDriver.findElement(By.xpath("(//div[@class='mainbody']/ul/li/a)[" + chapterLinkXPathIndex + "]"));
            String expectedUrl = chapterLink.getAttribute("href");
            Actions actions = new Actions(webDriver);
            actions.keyDown(Keys.CONTROL).click(chapterLink).keyUp(Keys.CONTROL).perform();
            // contextclick is not working :-
            // actions.contextClick(chapterLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            Set<String> windowHandleSet = webDriver.getWindowHandles();
            List<String> windowHandles = new ArrayList<>(windowHandleSet);
            for (int windowHandleIndex = 0; windowHandleIndex < windowHandles.size(); windowHandleIndex++) {
                String windowHandle = windowHandles.get(windowHandleIndex);
                if (!windowHandle.equals(parent)) {
                    webDriver.switchTo().window(windowHandle);
                    break;
                }
            }
            String currentUrl = webDriver.getCurrentUrl();
            //then
            assertEquals(currentUrl, expectedUrl);
            webDriver.close();
            webDriver.switchTo().window(parent);
        }
    }

}
