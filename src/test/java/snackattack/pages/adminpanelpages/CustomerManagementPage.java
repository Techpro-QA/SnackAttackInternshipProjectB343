package snackattack.pages.adminpanelpages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class CustomerManagementPage {
    public CustomerManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }



}
