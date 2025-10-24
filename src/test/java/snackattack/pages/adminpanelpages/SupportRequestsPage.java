package snackattack.pages.adminpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class SupportRequestsPage {
    public SupportRequestsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//select[@class='border px-4 py-2 mr-4']")
    public WebElement dropdownMenu;

    @FindBy(xpath = "//button[@class='px-3 py-2 bg-green-500 text-white rounded flex items-center justify-center']")
    public WebElement supportRequestViewButton;

    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    public WebElement confirmYesButton;

    @FindBy(xpath = "(//button[@class='px-3 py-2 bg-red-500 text-white rounded flex items-center justify-center'])[1]")
    public WebElement deleteButtton;

    @FindBy(id = "swal2-html-container")
    public WebElement successMsj;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> rows;

    @FindBy(xpath = "//button[@class='px-4 py-2 bg-blue-500 text-white rounded']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class='bg-white p-6 rounded-lg max-w-md w-full']")
    public WebElement detailOfRequest;

    @FindBy(xpath = "//div[contains(@class,'bg-white')]//h2")
    public WebElement messageDetailTitle;

    @FindBy(xpath = "//div[contains(@class,'bg-white')]//p")
    public List<WebElement> messageDetailParagraphs;

    @FindBy(xpath = "//input[@placeholder='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='subject']")
    public WebElement subjectInput;

    @FindBy(xpath = "//table/tbody/tr/td[3]")
    public List<WebElement> emailList;

    @FindBy(xpath = "//tr/td[4]")
    public WebElement subjectName;

    @FindBy(xpath = "//table//td[4]")
    public List<WebElement> subjectList;

    @FindBy(xpath = "//input[@type='date'])[1]")
    public WebElement dateInput1;

    @FindBy(xpath = "//input[@type='date'])[2]")
    public WebElement dateInput2;

    @FindBy(xpath = "(//input[@placeholder='HH'])[1]")
    public WebElement hour1;

    @FindBy(xpath = "(//input[@placeholder='MM'])[1]")
    public WebElement minute1;

    @FindBy(xpath = "(//input[@placeholder='HH'])[2]")
    public WebElement hour2;

    @FindBy(xpath = "(//input[@placeholder='MM'])[2]")
    public WebElement minute2;

    @FindBy(xpath = "//p[contains(text(),'Date:')]")
    public WebElement time;

}
