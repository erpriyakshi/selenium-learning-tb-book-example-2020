package uk.co.theautomatedtester.book;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePageBook {

    private final WebDriver webDriver;
    String url = "http://book.theautomatedtester.co.uk/";

    //@FindBy(className = "mainheading")
    @FindBy(how = How.CLASS_NAME, using = "mainheading")
    private WebElement mainHeading;

    @FindBy(xpath = "//div[@class='mainbody']")
    private WebElement description;

    // TODO create a scenario to practice @FindBys
    @FindBy(xpath = "//div[@class='mainbody']/ul/li/a")
    private List<WebElement> chapterLinks;

    @FindBy(xpath = "//div[@class='mainbody']/input")
    private WebElement inputField;
    // TODO what will happen if you use WebElement for a xpath returning multiple element
    // TODO what will happen if you use WebElement/List<WebElement> for a xpath returning no element

    @FindBy(xpath = "(//div[@class='mainbody']//ul//a)[1]")
    private WebElement chapter1;

    public HomePageBook(WebDriver webdriver) {
        this.webDriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void navigateToHomePage() {
        webDriver.get(url);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getMainHeadingText() {
        return mainHeading.getText();
    }

    public String getDescriptionText() {
        return description.getText();
    }


    public List<WebElement> getAllChapterLinks() {
        return chapterLinks;
    }

    public List<String> getAllChapterLinkNames() {
        List<String> allChapterLinkNames = new ArrayList<>();
        for (WebElement chapterLink : chapterLinks) {
            allChapterLinkNames.add(chapterLink.getText());
        }
        return allChapterLinkNames;
    }

    public void typeInputField(String inputData) {
        boolean enabled = inputField.isEnabled();
        if (enabled) {
            inputField.sendKeys(inputData);
        }
    }

    public String getInputFieldText() {
        return inputField.getAttribute("value");
    }

    public void clickOnChapter1() {
        chapter1.click();
    }



    public String clickLinkAndGetUrl(WebElement chapterLink) {
        chapterLink.click();
        String actualUrl = webDriver.getCurrentUrl();
        webDriver.navigate().back();
        return actualUrl;
    }
}

