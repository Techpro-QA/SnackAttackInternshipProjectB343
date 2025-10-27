package snackattack.pages.adminpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class AdminManagementPage {
    public AdminManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // "New" butonu — Yeni admin ekleme penceresini açar
    @FindBy(xpath = "//a[.='New']")
    public WebElement adminManagementNewButton;

    // "Confirm Password" giriş alanı
    @FindBy(xpath = "//input[@id='confirmPassword']")
    public WebElement confirmPasswordTextBox;


    // "Phone Number" giriş alanı
    @FindBy(xpath = "//input[@id='phoneNumber']")
    public WebElement phoneNumberTexBox;

    // "Register" butonu — Kayıt işlemini tamamlar
    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement registerButton;

    @FindBy(xpath = "//p[.='This field is required.']")
    public WebElement messageText;

    @FindBy(xpath = "//div[@class=\"go3958317564\"]")
    public WebElement registerSuccesfulMessageText;

    @FindBy(xpath = "//p[.='Password must be at least 4 characters.']")
    public WebElement passwordMustBeAtLeast4CharactersMessageText;

    @FindBy(xpath = "//p[.='Error : User with email nupyd@mailinator.com is ALREADY REGISTERED']")
    public WebElement erorMessageText;

    @FindBy(xpath = "//p")
    public WebElement invalidPhoneNumberMessageText;

    //Admin table locates

    @FindBy(tagName = "h1")
    public WebElement allAdminsTableTitle;

    @FindBy(tagName = "thead")
    public WebElement adminsTableHead;


    @FindBy(tagName = "tbody")
    public WebElement adminsTableBody;


    @FindBy(xpath = "//tbody/tr[1]/td[6]")
    public WebElement firstRowDeleteIcon;


    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    public WebElement firstRowAdminId;


    @FindBy(xpath = "(//a[@class='page-link'])[1]")
    public WebElement tableSecondPageButton;


    @FindBy(xpath = "//tbody/tr[4]/td[6]")
    public WebElement systemAdminDeleteIcon;


    @FindBy(xpath = "//tbody/tr[4]/td[1]")
    public WebElement systemAdminId;


    @FindBy(xpath = "(//div[.='Error deleting admin.'])[4]")
    public WebElement adminDeleteErrorAllert;
}