package snackattack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

public class ContactPage {
    public ContactPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "name")
    public WebElement nameTextBox;

    @FindBy(id = "email")
    public WebElement emailTextBox;

    @FindBy(id = "subject")
    public WebElement subjectTextBox;

    @FindBy(id = "message")
    public WebElement messageTextBox;

    @FindBy(id = "consent")
    public WebElement consentClickBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement SubmitContactButton;

    @FindBy(xpath= "//h3[contains(text(), 'Contact Message with id') and contains(text(), 'CREATED')]")
    public WebElement ContactMessageVerification;






}
