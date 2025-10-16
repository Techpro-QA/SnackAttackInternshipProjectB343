package snackattack.pages.adminpanelpages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class ProductManagementPage {
    public ProductManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }



}
