package uk.co.theautomatedtester.book;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Chapter1 {

    private WebDriver webDriver;

    @FindBy(className = "mainheading")
    private WebElement textHeading;

    @FindBy(xpath = "(//div[@class='mainbody']//p)[1]")
    private WebElement description;

    @FindBy(xpath = "//input[@id='radiobutton']")
    private WebElement radiobutton;

    @FindBy(id = "selecttype")
    private WebElement dropdown;

    @FindBy(xpath = "//select[@id='selecttype']//option")
    private List<WebElement> allDropdown;

    @FindBy(xpath = "//select[@id='selecttype']")
    private WebElement dropDown;

    @FindBy(xpath = "//input[@name='selected(1234)']")
    private WebElement checkBox;

    @FindBy(xpath = "//div[@id='divontheleft']")
    private WebElement textElement;

    @FindBy(id = "verifybutton")
    private WebElement button;

    @FindBy(xpath = "(//div[@id='multiplewindow'])[1]")
    private WebElement linkElement;

    @FindBy(id = "popuptext")
    private WebElement popTextElement;

    @FindBy(id = "closepopup")
    private WebElement closePopUpElement;

    public Chapter1(WebDriver webdriver) {
        this.webDriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public String getTextHeading() {
        return textHeading.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public boolean getRadioButtonState() {
        return radiobutton.isSelected();
    }

    public void selectRadioButton() {
        radiobutton.click();
    }

    public boolean getDropdownState() {
        return dropdown.isEnabled();
    }

    public int getDropdownSize() {
        return allDropdown.size();
    }

    public List<String> getAllDropdownValues() {
        List<String> actualDropDownValuesList = new ArrayList<>();
        Select dropDownSelect = new Select(dropDown);
        List<WebElement> options = dropDownSelect.getOptions();
        for (WebElement option : options) {
            actualDropDownValuesList.add(option.getText());
        }
        return actualDropDownValuesList;
    }

    public String getSelectedDropdownText() {
        Select dropDownSelect = new Select(dropDown);
        return dropDownSelect.getFirstSelectedOption().getText();
    }

    public void selectDropDownByText(String text) {
        Select dropDownSelect = new Select(dropDown);
        dropDownSelect.selectByVisibleText(text);
    }

    public boolean getCheckBoxEnableState() {
        return checkBox.isEnabled();
    }

    public boolean getCheckBoxInitialState() {
        return checkBox.isSelected();
    }

    public String getTextOnPage() {
        return textElement.getText();
    }

    public boolean getButtonPresence() {
        return button.isDisplayed();
    }

    public String getButtonLabelText() {
        return button.getAttribute("value");
    }

    public String getPopUpText() throws InterruptedException {
        String parent = webDriver.getWindowHandle();
        linkElement.click();
        Set<String> windowHandleSet = webDriver.getWindowHandles();
        List<String> windowHandles = new ArrayList<>(windowHandleSet);
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parent)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        webDriver.manage().window().maximize();
        // TODO: FIXME use explicit wait.
        Thread.sleep(3000);
        String popUpText = popTextElement.getText();
        closePopUpElement.click();
        return popUpText;
    }
}