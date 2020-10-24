package uk.co.theautomatedtester.book;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class HomePageBookTest extends BaseTest {

    HomePageBook homePageBook;

    @BeforeClass
    public void setUp() {
        super.setUp();
        homePageBook = new HomePageBook(webDriver);
        homePageBook.navigateToHomePage();
    }

    // verify Title "Selenium: Beginners Guide" of Home Page
    @Test
    public void testTitle() {
        //given
        String expectedTitle = "Selenium: Beginners Guide";

        //when
        String actualTitle = homePageBook.getTitle();

        //then
        assertEquals(actualTitle, expectedTitle);
    }

    //verify Page Heading
    @Test
    public void testPageHeading() {
        //given
        String expectedHeading = "Selenium: Beginners Guide";

        //when
        String actualHeading = homePageBook.getMainHeadingText();

        //then
        assertEquals(actualHeading, expectedHeading);
    }


    //verify Descriptions on HomePage
    @Test
    public void testDescriptions() {
        //given
        SoftAssert softAssert = new SoftAssert();
        String expectedDescriptions = "Below is a list of links to the examples needed in the chapters. Click on the links below and follow the steps in the book.";

        //when
        String actualDescriptionText = homePageBook.getDescriptionText();

        //then
        softAssert.assertNotNull(actualDescriptionText);
        softAssert.assertTrue(actualDescriptionText.startsWith(expectedDescriptions), "[" + actualDescriptionText + "] does not contains [" + expectedDescriptions + "]");
        softAssert.assertAll();
    }

    //verify link on homepage
    @Test(groups = "HomePageBookTest.testLinksNames")
    public void testLinksNames() {
        //given
        int expectedLinks = 5;
        List<String> expectedLinksName = new ArrayList<>();
        expectedLinksName.add("Chapter1");
        expectedLinksName.add("Chapter2");
        expectedLinksName.add("Chapter3");
        expectedLinksName.add("Chapter4");
        expectedLinksName.add("Chapter8");

        //when
        int actualLinks = homePageBook.getAllChapterLinks().size();
        List<String> actualLinksName = homePageBook.getAllChapterLinkNames();

        //then
        assertEquals(expectedLinks, actualLinks);
        assertEquals(actualLinksName, expectedLinksName);
    }


    //verify all links click one by one
    @Test
    public void testClickChapterLinksAndVerifyUrl() {
        //given
       int noOfChapterLinks = homePageBook.getAllChapterLinks().size();

        //when and then
        for (int index = 0; index <noOfChapterLinks ; index++) {
            WebElement chapterLink = homePageBook.getAllChapterLinks().get(index);
            String expectedUrl = chapterLink.getAttribute("href");
            String actualUrl = homePageBook.clickLinkAndGetUrl(chapterLink);
            assertEquals(actualUrl, expectedUrl);
        }

    }

    @Test(dependsOnMethods = {"testClickChapterLinksAndVerifyUrl"})
    public void testClicksAllLinksOpenInNewWindow() {
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
            for (String windowHandle : windowHandles) {
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

    //verify input field on home page
    @Test
    public void testInputField() {
        //given
        String expectedInputFieldText = "It is text input field";
        homePageBook.typeInputField("It is text input field");
        //when
        String actualInputFieldText = homePageBook.getInputFieldText();
        //then
        assertEquals(actualInputFieldText, expectedInputFieldText);
    }

}
