package uk.co.theautomatedtester.book;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Test (dependsOnGroups = "HomePageBookTest.testLinksNames")
public class Chapter1Test extends BaseTest {

    Chapter1 chapter1;

    @BeforeClass
    public void setUp() {
        super.setUp();
        HomePageBook homePageBook = new HomePageBook(webDriver);
        homePageBook.navigateToHomePage();
        homePageBook.clickOnChapter1();
        chapter1 = new Chapter1(webDriver);
    }
    // Verify Description and heading on Chapter1 Page
    @Test(priority = 1)
    public void testDescriptionsAndHeading() {
        //Given
        String expectedHeading = "Selenium: Beginners Guide";
        String expectedDescription = "If you have arrived here then you have installed Selenium IDE and are ready to start recording your first test.";
        //When
        String actualMainHeading = chapter1.getTextHeading();
        String actualDescription = chapter1.getDescription();
        //then
        Assert.assertEquals(actualMainHeading, expectedHeading);
        Assert.assertEquals(actualDescription, expectedDescription);
    }

    //verify radio button should be un-selected
    @Test(groups = {"RadioButtonGroupChapter1"}, priority = 2)
    public void testRadioButtonInitialState() {
        //given
        boolean expectedRadioButtonSelected = false;
        //when
        boolean initialStateOfRadioButtonSelected = chapter1.getRadioButtonState();
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
        chapter1.selectRadioButton();
        boolean radioButtonSelected = chapter1.getRadioButtonState();
        //then
        Assert.assertTrue(radioButtonSelected, "Radio Button is not selected");
    }

    // Verify the Drop down is Clickable
    @Test
    public void testDropDownEnable() {
        //given
        //when
       boolean actualDropDownState = chapter1.getDropdownState();
        //then
        Assert.assertTrue(actualDropDownState, "DropDown Is not enabled");
    }

    //Verify that the When Click on drop down then the Dropdown list Showing list
    @Test
    public void testDropDownSize() {
        //given
        int expectedDropDownListElementsSize = 4;
        //when
       int actualDropDownListElementsSize = chapter1.getDropdownSize();
        //then
        Assert.assertEquals(actualDropDownListElementsSize, expectedDropDownListElementsSize);
    }

    //verify that the drop down contains values as per SRS
    @Test
    public void testDropDownValues() {
        //given
        List<String> expectedDropDownValuesList = Arrays.asList("Selenium IDE", "Selenium Core", "Selenium RC", "Selenium Grid");
        //when
        List<String> actualDropDownValuesList = chapter1.getAllDropdownValues();

        //then
        Assert.assertEquals(actualDropDownValuesList, expectedDropDownValuesList);
    }

    //verify that the default item is getting displayed on drop down when the user first visits the page
    @Test
    public void testDefaultValueOfDropDown() {
        //given
        String expectedDefaultValue = "Selenium IDE";
        //when
        String actualDefaultValue = chapter1.getSelectedDropdownText();
        //then
        Assert.assertEquals(actualDefaultValue, expectedDefaultValue);
    }

    //Check that the selected category on drop down list is getting highlighted on selecting the item.
    @Test
    public void testSelectAndGetDropdownItem() {
        //given
        String expectedSelectedText = "Selenium Core";
        chapter1.selectDropDownByText(expectedSelectedText);
        //when
        String actualSelectedText =  chapter1.getSelectedDropdownText();
        //then
        Assert.assertEquals(actualSelectedText, expectedSelectedText);
    }

    //Verify that the User able to Select the Drop down by "up and down" key form the keyboard
    //Check that the drop-down can be scrolled up and down on mouse hover
    //verify checkbox is enabled and unchecked
    @Test
    public void testCheckBoxEnabledAndUnchecked() {
        //given
        //when
        boolean actualCheckboxEnabledStatus = chapter1.getCheckBoxEnableState();
        boolean actualCheckboxInitialState = chapter1.getCheckBoxInitialState();
        //then
        Assert.assertTrue(actualCheckboxEnabledStatus);
        Assert.assertFalse(actualCheckboxInitialState);
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
        String actualText =  chapter1.getTextOnPage();
        //then
        Assert.assertEquals(actualText, expectedText);
    }

    //verify button on the page
    @Test
    public void testButton() {
        //given
        String expectedButtonLabel = "Verify this button he here";
        //when
        boolean buttonPresence = chapter1.getButtonPresence();
        String actualButtonLabel = chapter1.getButtonLabelText();
        //then
        Assert.assertTrue(buttonPresence);
        Assert.assertEquals(actualButtonLabel, expectedButtonLabel);
    }

    //verify this link "Click this link to launch another window" after click on  redirect to another page
    // FIXME: fix this test case for window focus
    @Ignore
    @Test
    public void testLaunchAnotherWindowLink() throws InterruptedException {
        //given
        String expectedPopUpText = "Text within the pop up window";
        //when
        String actualPopUpText = chapter1.getPopUpText();
        //then
        Assert.assertEquals(actualPopUpText, expectedPopUpText);
    }

    //verify loadAjex div
    public void testLoadAjexDiv() {
        //TODO complete it by Monday (27-Oct-2020)
        // Question: mujhse hoga nahi?
        // Answer: try karo.
        // Counter argument by priyakshi: meine try kiya mujhse hua nhi.
        // Answer: JHOOT!! @#$$%@@#@
    }
}
