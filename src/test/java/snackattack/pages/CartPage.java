package snackattack.pages;

import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[.='Add to Cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='go2072408551']")
    public WebElement successfullyMassageText;

    @FindBy(xpath = "//div[@title=\"View Cart\"]")
    public WebElement viewCartIcon;

    @FindBy(xpath = "//button[.='Checkout']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//button[.='Create Order']")
    public WebElement createOrderButton;

    @FindBy(xpath = "//h2[.='Kartla Ödeme']")
    public WebElement odemeMassageText;

    @FindBy(xpath = "(//div[@class=\"w-32 h-32 rounded-full overflow-hidden bg-gray-200 mb-4\"])[1]")
    public WebElement tavukDonerMenu;

    @FindBy(xpath = "(//button[@class=\"flex flex-col items-center justify-center bg-gradient-animate px-4 py-2 rounded-lg border border-gray-300\"])[1]")
    public WebElement donerMenu;

    @FindBy(xpath = "(//div[@class=\"w-32 h-32 rounded-full overflow-hidden bg-gray-200 mb-4\"])[1]")
    public WebElement mayonezOption;


    @FindBy(xpath = "(//button[@class=\"px-3 py-1 rounded-full bg-gray-200 hover:bg-color3 transition\"])[2]")
    public WebElement ketcapOption;

    @FindBy(xpath = "//div[.='+']")
    public WebElement plusButton;

    @FindBy(xpath = "//div[.='-']")
    public WebElement minusButton;

    @FindBy(xpath = "//div[@class=\"text-lg font-semibold\"]")
    public WebElement counter;

    @FindBy(xpath = "//div[@class=\"go3958317564\"]")
    public WebElement itemAddedSuccessfullyMassageText;

    @FindBy(xpath = "(//tr//td[2])[1]")
    public WebElement productName;

    @FindBy(xpath = "(//tr//td[5])[1]")
    public WebElement productPrice;

}