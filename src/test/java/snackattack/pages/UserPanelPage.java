package snackattack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class UserPanelPage {
    public UserPanelPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[.='My Cart']")
    public WebElement userMyCartMenu;

    @FindBy(xpath = "//a[.='My Orders']")
    public WebElement userMyOrdersMenu;

    @FindBy(xpath = "//a[.='Settings']")
    public WebElement userSettingsMenu;






}
