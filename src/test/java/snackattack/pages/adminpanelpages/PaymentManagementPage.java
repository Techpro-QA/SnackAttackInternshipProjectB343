package snackattack.pages.adminpanelpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class PaymentManagementPage {
    public PaymentManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(tagName = "h1")
    public WebElement allPaymentsText;

    @FindBy(xpath = "//th[2]")
    public WebElement paymentIDHeader;

    @FindBy(xpath = "//th[3]")
    public WebElement orderIDHeader;

    @FindBy(xpath = "//th[4]")
    public WebElement userIDHeader;

    @FindBy(xpath = "//th[5]")
    public WebElement amountIDHeader;

    @FindBy(xpath = "//th[6]")
    public WebElement paymentDateHeader;

    @FindBy(xpath = "//table//thead//th[position()>1]") // 2. th’den itibaren tüm headerleri list sepetine koydum
    public List<WebElement> tableHeaders;

    @FindBy(xpath = "//table//tbody//tr") // tum satirlari aldik
    public List<WebElement> tableRows;

    @FindBy(xpath = "//button[.='Close']")
    public WebElement closeButton;

    @FindBy(xpath ="(//div[.='Order Details'])[1]")
    public WebElement orderDetailsText;

    @FindBy(xpath = "//p[1]")
    public WebElement orderCodeText;

    @FindBy(xpath = "//p[2]")
    public WebElement addressText;

    @FindBy(xpath = "//p[3]")
    public WebElement paymentMethodText;

    @FindBy(xpath = "//p[4]")
    public WebElement statusText;

    public WebElement getTableRow(int index) {
        List<WebElement> rows = Driver.getDriver().findElements(By.xpath("//table//tbody//tr"));
        return rows.get(index);
    }

    @FindBy(xpath = "//span[.='More']")
    public WebElement morePageButton;

    @FindBy(xpath = "//a[text()='2']")
    public WebElement secondPageButton;

    @FindBy(xpath = "//a[text()='3']")
    public WebElement thirdPageButton;

    @FindBy(xpath = "(//table//tbody//tr)[1]")
    public WebElement firstRow;

    @FindBy(xpath = "(//table//tbody//tr)[2]")
    public WebElement secondRow;

    @FindBy(xpath = "(//table//tbody//tr)[3]")
    public WebElement thirdRow;

    @FindBy(xpath = "(//table//tbody//tr)[4]")
    public WebElement fourthRow;

    @FindBy(xpath = "(//table//tbody//tr)[5]")
    public WebElement fifthRow;


    @FindBy(xpath = "(//table//tbody//tr//td)[2]")
    public WebElement firstRowPaymentId;


    @FindBy(xpath = "(//table//tbody//tr//td)[3]")
    public WebElement firstRowOrderId;


    @FindBy(xpath = "(//table//tbody//tr//td)[4]")
    public WebElement firstRowUserId;

    @FindBy(xpath = "(//table//tbody//tr//td)[5]")
    public WebElement firstRowAmount;

    @FindBy(xpath = "(//table//tbody//tr//td)[6]")
    public WebElement firstRowPaymentDate;


}
