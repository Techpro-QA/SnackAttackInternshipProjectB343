package snackattack.pages.adminpanelpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import snackattack.utilities.Driver;

public class OrderManagementPage {
    public OrderManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='container']")
    public WebElement AdminOrderlist;

    @FindBy(xpath = "//h1[contains(text(),'Order Details')]")
    public WebElement OrderDetailsHeader;

 @FindBy(xpath = "(//table//tbody//tr)[1]")
    public WebElement ilkSiparis;

@FindBy(xpath = "//*[contains(text(),'Order Details')]")
    public WebElement orderDetailsYazisi;


}
