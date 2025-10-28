package snackattack.pages.adminpanelpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class CategoryManagementPage {
    public CategoryManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    //
    @FindBy(tagName = "h3")
    public WebElement categoryManagementPanelTittle;


    //
    @FindBy(xpath = "//a[.='New']")
    public WebElement categoryManagementNewButton;


    //
    @FindBy(name = "name")
    public WebElement createCategoryNameTextBox;



    //
    @FindBy(name = "active")
    public WebElement createCategoryActiveCheckBox;


    //
    @FindBy(xpath = "//button[.='Save']")
    public WebElement createCategorySaveButton;



    //
    @FindBy(xpath = "//tbody")
    public List<WebElement> allCategoryTable;




    //
    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    public WebElement firstRowDeleteButton;


    //
    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement firstRowCategoryName;



    //
    @FindBy(tagName = "p")
    public WebElement somethingWentWrongPleaseTryAgainText;


    //
    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    public WebElement firstRowActiveCell;



    //
    @FindBy(xpath = "//tbody/tr[2]/td[3]")
    public WebElement secondRowActiveCell;


    //
    @FindBy(xpath = "//tbody/tr[3]/td[3]")
    public WebElement thirdRowActiveCell;






//






}
