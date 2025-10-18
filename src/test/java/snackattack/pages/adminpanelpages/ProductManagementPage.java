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

    // Login error mesajı
    @FindBy(xpath = "//p[@class='text-red-500 text-sm']")
    public WebElement loginErrorMessage;

    //Product güncelleme textbox locateleri

    @FindBy(xpath = "//input[@accept='image/*']")
    public WebElement updateSellectFile;

    @FindBy(name = "name")
    public WebElement updateProductNameTextbox;

    @FindBy(name = "description")
    public WebElement updateDescriptionTextbox;

    @FindBy(name = "contents")
    public WebElement updateContentsTextbox;

    @FindBy(name = "price")
    public WebElement updatePriceTextbox;

    @FindBy(name = "discount")
    public WebElement updateDiscountTextbox;

    //Kategoriler checboxlari locateleri

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    public WebElement updatePIZZACheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    public WebElement updateIceceklerCheckbox;

     @FindBy(xpath = "(//input[@type='checkbox'])[3]")
    public WebElement updateAtistirmaliklarCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[4]")
    public WebElement updatePİZZACheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[5]")
    public WebElement updateTATLILARCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[6]")
    public WebElement updatepizzaCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[7]")
    public WebElement updateMEZELERCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[8]")
    public WebElement updateSALATALARCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[9]")
    public WebElement updateDenemeCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[10]")
    public WebElement updateDONERCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[11]")
    public WebElement updateHAMBURGERCheckbox;

    //Ek Kategoriler locate

    @FindBy(xpath = "(//input[@type='checkbox'])[12]")
    public WebElement updateSoslarCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[13]")
    public WebElement updateSalataSoslariCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[14]")
    public WebElement updateMezelerCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[15]")
    public WebElement updatePopülerCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[16]")
    public WebElement updateMevcutCheckbox;






}
