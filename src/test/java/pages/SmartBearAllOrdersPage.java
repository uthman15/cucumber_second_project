package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class SmartBearAllOrdersPage {

    public SmartBearAllOrdersPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]")
    public WebElement myIndividualOrder;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td")
    public List<WebElement> myIndividualOrderList;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteSelectedButton;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr")
    public List<WebElement> fullTableList;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement deletedTableMessage;
}