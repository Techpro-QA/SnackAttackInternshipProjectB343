package snackattack.pages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class ContactPage {
    public ContactPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
