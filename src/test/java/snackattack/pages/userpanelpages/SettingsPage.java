package snackattack.pages.userpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class SettingsPage {
    public SettingsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "userName")
    public WebElement settingUsernameTextbox ;

    @FindBy(name = "email")
    public WebElement settingEmailTextBox ;

    @FindBy(name = "firstName")
    public WebElement settingFirstNameTextbox ;

    @FindBy(name = "lastName")
    public WebElement settingLastNameTextbox ;

    @FindBy(name = "address")
    public WebElement settingAddressTextbox ;

    @FindBy(name = "phoneNumber")
    public WebElement settingPhoneNumber ;

    @FindBy(name = "regionCode")
    public WebElement settingRegionCode ;

    @FindBy(name = "password")
    public WebElement settingPassword ;

    @FindBy(xpath = "//button[.='Update Profile']")
    public WebElement settingUpdateProfileButton ;


}
