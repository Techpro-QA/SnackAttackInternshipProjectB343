package snackattack.pages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
