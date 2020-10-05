package uk.co.theautomatedtester.book;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

// TODO: this test call should only run if homePageBookTest.testClicksAllLinks() is passed
//@Test (dependsOnGroups = "HomePageBookTest")
public class Chapter1Test extends BaseTest {


    @BeforeClass
    public void setup() {
        webDriver.findElement(By.xpath("(//div[@class='mainbody']//ul//a)[1]")).click();
    }

    // Verify Description and heading on Chapter1 Page
    @Test(priority = 1)
    public void testDescriptionsAndHeading() {
        //Given
        String expectedHeading = "Selenium: Beginners Guide";
        String expectedDescription = "If you have arrived here then you have installed Selenium IDE and are ready to start recording your first test.";
        //When
        WebElement mainHeadingElement = webDriver.findElement(By.className("mainheading"));
        String actualMainHeading = mainHeadingElement.getText();
        WebElement descriptionElement = webDriver.findElement(By.xpath("(//div[@class='mainbody']//p)[1]"));
        String actualDescription = descriptionElement.getText();
        //then
        Assert.assertEquals(actualMainHeading, expectedHeading);
        Assert.assertEquals(actualDescription, expectedDescription);
    }

    //verify radio button should be un selected
    @Test(groups = {"RadioButtonGroupChapter1"}, priority = 2)
    public void testRadioButtonInitialState() {
        //given
        boolean expectedRadioButtonSelected = false;
        //when
        WebElement radioButtonElement = webDriver.findElement(By.xpath("//input[@id='radiobutton']"));
        boolean initialStateOfRadioButtonSelected = radioButtonElement.isSelected();
        //then
        Assert.assertFalse(initialStateOfRadioButtonSelected, "Radio Button is Selected");
    }

    //verify radio button on the chapter1 page get selected on click
    //TODO convert this dependency from method to group
    //@Test(dependsOnMethods = "testRadioButtonInitialState")
    @Test(dependsOnGroups = "RadioButtonGroupChapter1")
    public void testRadioButton() {
        //given
        boolean expectedRadioButtonSelected = true;
        //when
        WebElement radioButtonElement = webDriver.findElement(By.xpath("//input[@id='radiobutton']"));
        radioButtonElement.click();
        boolean radioButtonSelected = radioButtonElement.isSelected();
        //then
        Assert.assertTrue(radioButtonSelected, "Radio Button is not selected");
    }

    // Verify the Drop down is Clickable
    @Test
    public void testDropDownEnable() {
        //given
        boolean dropdownEnableState = true;
        //when
        WebElement dropdownElement = webDriver.findElement(By.id("selecttype"));
        boolean actualDropDownState = dropdownElement.isEnabled();
        //then
        Assert.assertTrue(actualDropDownState, "DropDown Is not enabled");
    }

    //Verify that the When Click on drop down then the Dropdown list Showing list
    @Test
    public void testDropDownSize() {
        //given
        int expectedDropDownListElementsSize = 4;
        //when
        List<WebElement> actualListShowingElement = webDriver.findElements(By.xpath("//select[@id='selecttype']//option"));
        int actualDropDownListElementsSize = actualListShowingElement.size();
        //then
        Assert.assertEquals(actualDropDownListElementsSize, expectedDropDownListElementsSize);
    }

    //verify that the drop down contains values as per SRS
    @Test
    public void testDropDownValues() {
        //given
        List<String> expectedDropDownValuesList = Arrays.asList("Selenium IDE", "Selenium Core", "Selenium RC", "Selenium Grid");
        //when
        List<String> actualedDropDownValuesList = new ArrayList<>();
        List<WebElement> dropdownValuesElementsList = webDriver.findElements(By.xpath("//select[@id='selecttype']//option"));
        for (int indexForDropDownValues = 1; indexForDropDownValues <= dropdownValuesElementsList.size(); indexForDropDownValues++) {
            WebElement dropdownElement = webDriver.findElement(By.xpath("(//select[@id='selecttype']//option)[" + indexForDropDownValues + "]"));
            String actualdropDownValue = dropdownElement.getText();
            actualedDropDownValuesList.add(actualdropDownValue);
        }
        //then
        Assert.assertEquals(actualedDropDownValuesList, expectedDropDownValuesList);
    }

    //verify that the default item is getting displayed on drop down when the user first visits the page
    @Test
    public void testDefaultValueOfDropDown() {
        //given
        String expectedDefaultValue = "SSelenium IDE";
        //when
        WebElement defaultElement = webDriver.findElement(By.xpath(
                "(//select[@id='selecttype']//option)[1]"));
        String actualDefaultValue = defaultElement.getAttribute("value");
        //TODO need to discuss with sumit
        String actualDefaultValue1 = defaultElement.getText();
        //then
        Assert.assertEquals(actualDefaultValue, expectedDefaultValue);
    }

    //Check that the selected category on drop down list is getting highlighted on selecting the item.
    @Test
    public void testSelectDropdownItem() {
        //given
        String expectedSelectedItem = "Selenium Core";
        //when

        WebElement dropDownElement = webDriver.findElement(By.xpath("(//select[@id='selecttype']//option)[2]"));
        dropDownElement.click();
        String actualSelectedValue = dropDownElement.getText();
        //then
        Assert.assertEquals(actualSelectedValue, expectedSelectedItem);
    }

    //Verify that the User able to Select the Drop down by "up and down" key form the keyboard
    //Check that the drop-down can be scrolled up and down on mouse hover
    //verify checkbox is enabled and unchecked
    @Test
    public void testCheckBoxEnabledAndUnchecked() {
        //given
        //when
        WebElement checkboxElement = webDriver.findElement(By.xpath("//input[@name='selected(1234)']"));
        boolean actualCheckboxInitialState = checkboxElement.isSelected();
        boolean actualCheckboxEnabledStatus = checkboxElement.isEnabled();
        //then
        Assert.assertFalse(actualCheckboxInitialState);
        Assert.assertTrue(actualCheckboxEnabledStatus);
    }

    //verify checkbox is selected after click on
    @Test(dependsOnMethods = "testCheckBoxEnabledAndUnchecked")
    public void testCheckBoxIsSelectedAndDeSelected() {
        //given
        boolean expectedStateAfterSelect = true;
        boolean expectedStateAfterDeSelect = false;
        //when
        WebElement checkboxElement = webDriver.findElement(By.xpath("//input[@name='selected(1234)']"));
        checkboxElement.click();
        boolean actualStateAfterSelect = checkboxElement.isSelected();
        checkboxElement.click();
        boolean actualStateAfterDeSelect = checkboxElement.isSelected();
        //then
        Assert.assertEquals(actualStateAfterSelect, expectedStateAfterSelect);
        Assert.assertEquals(actualStateAfterDeSelect, expectedStateAfterDeSelect);
    }

    //verify this Text"Assert that this text is on the page" on page
    @Test
    public void testTextOnPage() {
        //given
        String expectedText = "Assert that this text is on the page";
        //when
        WebElement textElement = webDriver.findElement(By.xpath("//div[@id='divontheleft']"));
        String actualText = textElement.getText();
        //then
        Assert.assertEquals(actualText, expectedText);
    }

    //verify button on the page
    @Test
    public void testButton() {
        //given
        String expectedButtonLabel = "Verify this button he here";
        //when
        WebElement buttonElement = webDriver.findElement(By.id("verifybutton"));
        boolean buttonPresence = buttonElement.isDisplayed();
        String actualButtonLabel = buttonElement.getAttribute("value");
        //then
        Assert.assertTrue(buttonPresence);
        Assert.assertEquals(actualButtonLabel, expectedButtonLabel);
    }

    //verify this link "Click this link to launch another window" after click on  redirect to another page
    @Test
    public void testLaunchAnotherWindowLink() throws InterruptedException {
        //given
        String expectedPopUpText = "Text within the pop up window";
        String parent = webDriver.getWindowHandle();
        //when
        WebElement linkElement = webDriver.findElement(By.xpath("(//div[@id='multiplewindow'])[1]"));
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
        Thread.sleep(3000);
        WebElement popuptextElement = webDriver.findElement(By.id("popuptext"));
        String actualPopUpText = popuptextElement.getText();
        WebElement closePopUpElement = webDriver.findElement(By.id("closepopup"));
        closePopUpElement.click();
        //then
        Assert.assertEquals(actualPopUpText, expectedPopUpText);
    }

    //verify loadAjex div
    public void testLoadAjexDiv() {
        //TODO complete it by tomorrow
    }
}
