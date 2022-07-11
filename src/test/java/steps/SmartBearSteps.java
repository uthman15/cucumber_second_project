package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SmartBearAllOrdersPage;
import pages.SmartBearHomePage;
import pages.SmartBearLoginPage;
import pages.SmartBearOrdersPage;
import utils.Driver;
import utils.DropdownHandler;

public class SmartBearSteps {

    WebDriver driver;
    SmartBearLoginPage smartBearLoginPage;
    SmartBearHomePage smartBearHomePage;
    SmartBearOrdersPage smartBearOrdersPage;
    SmartBearAllOrdersPage smartBearAllOrdersPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        smartBearLoginPage = new SmartBearLoginPage();
        smartBearHomePage = new SmartBearHomePage();
        smartBearOrdersPage = new SmartBearOrdersPage();
        smartBearAllOrdersPage = new SmartBearAllOrdersPage();
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBearLoginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearLoginPage.passwordInputBox.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearLoginPage.loginButton.click();
    }

    @Then("user should see {string} Message")
    public void userShouldSeeMessage(String key) {
        Assert.assertTrue(smartBearLoginPage.loginErrorMessage.isDisplayed());
        Assert.assertEquals(key, smartBearLoginPage.loginErrorMessage.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        for (int i = 0; i < smartBearHomePage.menuItems.size(); i++) {
            Assert.assertTrue(smartBearHomePage.menuItems.get(i).isDisplayed());
            Assert.assertEquals(dataTable.asList().get(i), smartBearHomePage.menuItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String key) {
        switch (key){
            case "Check All":
                smartBearHomePage.checkAllButton.click();
                break;
            case "Uncheck All":
                smartBearHomePage.uncheckAllButton.click();
                break;
            case "Process":
                smartBearOrdersPage.processButton.click();
                break;
            case "Delete Selected":
                smartBearAllOrdersPage.deleteSelectedButton.click();
                break;
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (int i = 0; i < smartBearHomePage.checkBoxesList.size(); i++) {
            Assert.assertTrue(smartBearHomePage.checkBoxesList.get(i).isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (int i = 0; i < smartBearHomePage.checkBoxesList.size(); i++) {
            Assert.assertFalse(smartBearHomePage.checkBoxesList.get(i).isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String key) {
        switch (key){
            case "Order":
                for (int i = 0; i < smartBearHomePage.menuItems.size(); i++) {
                    smartBearHomePage.menuItems.get(2).click();
                }
                break;
            case "View all orders":
                for (int i = 0; i < smartBearHomePage.menuItems.size(); i++) {
                    smartBearHomePage.menuItems.get(0).click();
                }
                break;
        }

    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String key) {
        DropdownHandler.selectOptionByValue(smartBearOrdersPage.productDropdown, "FamilyAlbum");
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int key) {
        smartBearOrdersPage.quantityInputBox.sendKeys(String.valueOf(key));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        smartBearOrdersPage.customerNameInputBox.sendKeys("John Doe");
        smartBearOrdersPage.streetInputBox.sendKeys("1234 Chicago Ave");
        smartBearOrdersPage.cityInputBox.sendKeys("Chicago");
        smartBearOrdersPage.stateInputBox.sendKeys("Illinois");
        smartBearOrdersPage.zipcodeInputBox.sendKeys("12345");
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBearOrdersPage.cardTypeRadioButtons.get(1).click();
        smartBearOrdersPage.cardNumberInputBox.sendKeys("1234123412341234");
        smartBearOrdersPage.expirationDateInputBox.sendKeys("01/25");
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String tableName) {
        Assert.assertTrue(smartBearAllOrdersPage.myIndividualOrder.isDisplayed());
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder(DataTable dataTable) {
        for (int i = 1; i < smartBearAllOrdersPage.myIndividualOrderList.size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearAllOrdersPage.myIndividualOrderList.get(i).getText());
        }
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String table) {
        Assert.assertTrue(smartBearAllOrdersPage.fullTableList.size() == 0);
    }

    @And("validate user sees {string} Message")
    public void validateUserSeesMessage(String message) {
        Assert.assertEquals(message, smartBearAllOrdersPage.deletedTableMessage.getText());
        Assert.assertTrue(smartBearAllOrdersPage.deletedTableMessage.isDisplayed());
    }
}