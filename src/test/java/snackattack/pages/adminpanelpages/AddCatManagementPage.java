package snackattack.pages.adminpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class AddCatManagementPage {
    public AddCatManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

// Add Kategori sayfasi yonetimi panelindeki Locateler //

    @FindBy(xpath = "//h3[text()='Add Kategori sayfası yönetimi paneli']")
    public WebElement addKategoriSayfasiYonetimPaneli;

    @FindBy(xpath = "//a[@href='/dashboard/admin/addition']")
    public WebElement additionButton;

    // Table header (başlık) hücrelerini bul
    @FindBy(xpath = "//table[contains(@class,'min-w-full')]//thead//th")
    public List<WebElement> tableHeaders;

    // Add Kategori sayfasi yonetimi panelindeki New button
    @FindBy(xpath = "//button[text()='New']")
    public WebElement newButton;

    // Enter Category Name
    @FindBy(xpath = "//input[@placeholder='Enter category name']")
    public WebElement enterCategoryName;

    // Enter Category Name : Add button
    @FindBy(xpath = "//button[text()='Add']")
    public WebElement addButton;

    // Enter Category Name : Cancel button
    @FindBy(xpath = "//button[text()='Cancel']")
    public WebElement cancelButton;

    // Table Name sütun locate
    @FindBy(xpath = "//table//tbody/tr/td[2]")
    public List<WebElement> nameSutunListLocate;

    // Add Kategori sayfasi yonetimi panelindeki silme iconu
    @FindBy(xpath = "//table//tr[2]//td[5]/div/*")
    public WebElement deleteIconForDeneme;

// Add Kategori sayfasi yonetimi panelindeki Locateler //


    @FindBy(xpath = "//input[@type='text']")
    public WebElement nameTextBox;

    @FindBy(xpath = "//textarea")
    public WebElement descriptionTextArea;

    @FindBy(xpath = "//input[@type='number']")
    public WebElement priceTextBox;

    @FindBy(xpath = "//select")
    public WebElement ddm;

    @FindBy(xpath = "//table[contains(@class,'min-w-full')]//tbody//tr[last()]//td")
    public List<WebElement> lastAdditionOnList;





}






