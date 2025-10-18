package snackattack.pages.adminpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class ProductManagementPage {
    public ProductManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Ürün tablosu
    @FindBy(xpath = "//table[@class='min-w-full border-collapse border border-gray-200']")
    public WebElement productTable;

    // Ürün listesi (tablodaki ilk satır – varsa)
    @FindBy(xpath = "//tbody/tr[1]")
    public WebElement firstProductRow;

    // Tüm ürün satırları
    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> allProductRows;

    // Arama kutusu
    @FindBy(id = "search")
    public WebElement searchBox;

    // Search butonu
    @FindBy(xpath = "//button[.='Search']")
    public WebElement searchButton;

    // "Ürün bulunamadı" mesajı
    @FindBy(xpath = "//*[contains(text(),'Ürün bulunamadı')]")
    public WebElement noProductFoundMessage;


    // Login error mesajı
    @FindBy(xpath = "//p[@class='text-red-500 text-sm']")
    public WebElement loginErrorMessage;



}
