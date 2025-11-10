package snackattack.pages.userpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class MyCartPage {
    public MyCartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h2[@class='p-0 m-0 text-lg font-semibold truncate']")
    public WebElement selectedProduct;

    @FindBy(xpath = "//*[contains(text(),'Dashboard')]")
    public WebElement dashboard;

    @FindBy(xpath = "//tbody//tr/td[2]")
    public WebElement selectedProductSecondWay;

    @FindBy(xpath = "//button[.='Clear Cart']")
    public WebElement clearCartButton;

    @FindBy(xpath = "(//button[.='Clear Cart'])[2]")
    public WebElement confirmClearCartButton;


    @FindBy(xpath = "//*[@d='m17.21 9-4.38-6.56a.993.993 0 0 0-.83-.42c-.32 0-.64.14-.83.43L6.79 9H2c-.55 0-1 .45-1 1 0 .09.01.18.04.27l2.54 9.27c.23.84 1 1.46 1.92 1.46h13c.92 0 1.69-.62 1.93-1.46l2.54-9.27L23 10c0-.55-.45-1-1-1h-4.79zM9 9l3-4.4L15 9H9zm3 8c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2z']")
    public WebElement cartManagementPanelCartIkon;

    @FindBy(xpath = "//h2[.='Your cart is empty.']")
    public WebElement confirmClearSuccessful;

    @FindBy(xpath = "//div[.='+']")
    public WebElement plusIkon;

    @FindBy(xpath = "//div[.='-']")
    public WebElement minusIkon;

    @FindBy(xpath = "//div[@class='go3958317564']")
    public WebElement quantityUpdatedConfirmation;

    @FindBy(xpath = "//button[@class='btn d-flex align-items-center dropdown-toggle btn btn-primary']")
    public WebElement userIcon;

    @FindBy(xpath = "//button[.='Checkout']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//button[.='Create Order']")
    public WebElement createOrderButton;

    @FindBy(xpath = "//*[@class='w-full p-2 rounded-md bg-white/20 text-white border border-white/30 focus:outline-none']")
    public WebElement ddmKartlaOdeme;

    @FindBy(xpath = "//*[.='Ödeme Yap']")
    public WebElement odemeYap;

    @FindBy(xpath = "//input[.='CREDIT_CARD']")
    public List<WebElement> creditCardTextBox; //böyle bir button ve sayfa yok, olmadıgını dogrulamak için yazıldı

    @FindBy(xpath = "//button[.='Ok']")
    public WebElement okButton;

    @FindBy(xpath = "//p[@class='text-sm text-left font-semibold']")
    public WebElement statusElement;


}
