package snackattack.pages.adminpanelpages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class AdminManagementPage {
    public AdminManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }



}
