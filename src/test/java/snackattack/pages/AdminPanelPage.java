package snackattack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class AdminPanelPage {

    public AdminPanelPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //Admin Panel menü locates

    @FindBy(xpath = "//a[.='Customer Management']")
    public WebElement customerManagementMenu;

     @FindBy(xpath = "//a[.='Admin Management']")
    public WebElement adminManagementMenu;

     @FindBy(xpath = "//a[.='Order Management']")
    public WebElement orderManagementMenu;

     @FindBy(xpath = "//a[.='Product Management']")
    public WebElement productManagementMenu;

     @FindBy(xpath = "//a[.='Category Management']")
    public WebElement categoryManagementMenu;

     @FindBy(xpath = "//a[.='Payment Management']")
    public WebElement paymentManagementMenu;

     @FindBy(xpath = "//a[.='Add. Cat. Management']")
    public WebElement addCatManagementMenu;

     @FindBy(xpath = "//a[.='Support Requests']")
    public WebElement supportRequestsMenu;



}
