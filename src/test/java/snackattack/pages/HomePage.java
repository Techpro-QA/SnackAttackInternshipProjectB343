package snackattack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //Login Locates
    @FindBy(xpath = "//div[@class='hover:cursor-pointer text-color3']")
    public WebElement homeLoginRegisterButton;

    @FindBy(id = "email")
    public WebElement loginEmailTextBox;

    @FindBy(id = "password")
    public WebElement loginPasswordTextBox;

    @FindBy(tagName = "button")
    public WebElement loginButton;

    //Header Locates

    @FindBy(xpath = "//img[@class='w-auto h-auto ps-8']")
    public WebElement homeLogo;

    @FindBy(xpath = "//a[.='Products']")
    public WebElement homeProducts;

    @FindBy(xpath = "//a[.='About']")
    public WebElement homeAbout;

    @FindBy(xpath = "//a[.='Contact']")
    public WebElement homeContact;

    @FindBy(xpath = "//div[@title='View Cart']")
    public WebElement homeViewCart;












}
