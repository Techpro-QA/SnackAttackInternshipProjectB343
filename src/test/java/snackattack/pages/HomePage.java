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

    //Register locates
    @FindBy(id = "password")
    public WebElement registerPasswordTextBox;

    @FindBy(id = "confirmPassword")
    public WebElement registerConfirmPasswordTextBox;

    @FindBy(xpath = "//a[text()='Register']")
    public WebElement registerButton;

    @FindBy(id = "firstName")
    public WebElement registerFirstNameTextBox;

    @FindBy(id = "lastName")
    public WebElement registerLastNameTextBox;

    @FindBy(xpath = "//input[@id='email' and @type='text']")
    public WebElement registerEmailTextBox;

    @FindBy(id = "userName")
    public WebElement registerUserNameTextBox;

    @FindBy(id = "address")
    public WebElement registerAddressTextBox;

    @FindBy(id = "regionCode")
    public WebElement registerCountryCodeSelect;

    @FindBy(id = "phoneNumber")
    public WebElement registerPhoneNumberTextBox;

    @FindBy(css = "div[role='status']")
    public WebElement authLoginErrorStatus;

    @FindBy(xpath = "(//button[@type='submit' and contains(.,'Register') and not(@disabled)])[1]" )
    public WebElement registerSubmitButton;

    @FindBy(xpath = "//div[@role='status' and @aria-live='polite' and contains(translate(., 'SUCCESS', 'success'), 'success')]" )
    public WebElement registerSuccessMessage;

    @FindBy(css = "p.text-red-500.text-xs")
    public WebElement requiredFieldErrorMessage;

    @FindBy(css = "p.text-red-500.text-sm")
    public WebElement loginRequiredFieldErrorMessage;

    @FindBy(css = "div[aria-live='polite']")
    public WebElement registerStatusMessagePopup;

    @FindBy(linkText = "Logout")
    public WebElement logoutButton;

    @FindBy(id = "email")
    public WebElement forgotPasswordEmailTextBox;

    @FindBy(xpath = "//button[normalize-space()='Reset Password']")
    public WebElement resetPasswordButton;

    @FindBy(css = "a[href='/forgot-password']")
    public WebElement forgotPasswordLink;

    @FindBy(xpath = "//h1[contains(text(),'Wellcome to Customer Panel')]")
    public WebElement userPanelTitle;

    @FindBy(xpath = "//p[@class='text-red-500 text-xs' and text()='Password must be at least 4 characters.']")
    public WebElement password4CharactersErrorMessage;

    @FindBy(xpath = "//p[@class='text-red-500 text-xs' and text()='Passwords must match.']")
    public WebElement passwordsMustMatchMessage;

    @FindBy(xpath = "//p[@class='text-red-500 text-xs' and text()='Invalid phone number.']")
    public WebElement invalidPhoneNumberMessage;

    @FindBy(xpath = "//p[@class='text-red-500 text-xs' and normalize-space()='Invalid phone number.']")
    public WebElement registerInvalidPhoneNumberMessage;

    @FindBy(xpath = "//p[contains(text(),'Invalid email')]")
    public WebElement invalidEmailMessage;

    @FindBy(xpath = "//div[contains(text(), \"Unexpected token\")]")
    public WebElement emailErrorMessage;









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
