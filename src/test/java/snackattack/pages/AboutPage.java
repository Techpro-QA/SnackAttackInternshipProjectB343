package snackattack.pages;

import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class AboutPage {
    public AboutPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
