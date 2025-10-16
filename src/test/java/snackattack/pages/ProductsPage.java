package snackattack.pages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class ProductsPage {
    public ProductsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }



}
