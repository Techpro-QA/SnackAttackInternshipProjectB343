package snackattack.pages.adminpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class CustomerManagementPage {
    public CustomerManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(tagName = "h1")
    public WebElement allUsersTableTitle;

    @FindBy(tagName = "thead")
    public WebElement usersTableHead;

    @FindBy(tagName = "tbody")
    public WebElement usersTableBody;

    @FindBy(xpath = "//tbody/tr[1]/td[6]")
    public WebElement firstRowDeleteIcon;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    public WebElement firstRowUserId;

    @FindBy(xpath = "//tbody/tr[20]/td[1]")
    public WebElement lastRowUserId;

    @FindBy(xpath = "//tbody/tr[20]/td[6]")
    public WebElement lastRowDeleteIcon;

}
