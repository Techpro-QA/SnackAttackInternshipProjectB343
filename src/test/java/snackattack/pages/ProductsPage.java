package snackattack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class ProductsPage {

    public ProductsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // TC_01: "Tatli 3333" başlığı (tam eşleşme)
    @FindBy(xpath = "//h2[normalize-space()='Tatli 3333']")
    public WebElement productNameTatli3333;

    // Liste yüklendi mi beklemek için kart başlıkları
    @FindBy(xpath = "//div[contains(@class,'card')]//h2")
    public List<WebElement> productTitles;

    // Pagination kökü
    @FindBy(xpath = "//ul[contains(@class,'pagination')]")
    public WebElement pagination;

    // Aktif sayfa göstergesi (a veya span)
    @FindBy(xpath = "//ul[contains(@class,'pagination')]//li[contains(@class,'active')]//*[self::a or self::span]")
    public WebElement activePageIndicator;

    // "Next" butonunu içeren <li> (disabled kontrolü için)
    @FindBy(xpath = "//ul[contains(@class,'pagination')]//li[.//*[normalize-space()='Next' or normalize-space()='›' or normalize-space()='>']]")
    public WebElement nextLi;

    // "Next" tıklanabilir <a>
    @FindBy(xpath = "//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and @role='button'][./span[normalize-space()='Next'] or normalize-space()='›' or normalize-space()='>']")
    public WebElement nextButton;


    @FindBy(id = "search")
    public WebElement searchInput;

    // "Search" butonu
    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//*[contains(normalize-space(),'Sonuç bulunamadı') or contains(normalize-space(),'No results')]")
    public WebElement emptyStateMsg;

    //sol üst köşedeki ilk ürün
    @FindBy(xpath = "(//div[@class='w-64 h-96 bg-color4 bg-opacity-50 rounded-lg mb-10 p-4 shadow-lg flex flex-col items-center text-center cursor-pointer hover:bg-color2 hover:scale-95 transition duration-500 hover-glow '])[1]")
    public WebElement firstProduct;
}