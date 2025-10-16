package snackattack.pages.adminpanelpages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class AddCatManagementPage {
    public AddCatManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
