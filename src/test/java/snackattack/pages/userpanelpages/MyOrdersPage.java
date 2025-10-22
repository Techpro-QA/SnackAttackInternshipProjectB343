package snackattack.pages.userpanelpages;

import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import snackattack.utilities.Driver;

import java.util.List;

public class MyOrdersPage {
    public MyOrdersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //MyOrders page locates

    //butun siparis kartlari
    @FindBy (xpath = "//div[@class='p-4']")
    public List<WebElement> myOrdersMenu ;


    //Delivered durumundaki order in numarasi
    @FindBy (xpath = "(//h2[@class='text-xl text-left font-semibold'])[1]")
    public WebElement ordersNumberStatusCanceled ;

    //Canceled durumundaki order in numarasi
    @FindBy (xpath = "(//h2[@class='text-xl text-left font-semibold'])[2]")
    public WebElement ordersNumberStatusPending ;

    //Confirmed durumundaki order in numarasi
    @FindBy (xpath = "(//h2[@class='text-xl text-left font-semibold'])[3]")
    public WebElement ordersNumberStatusConfirmed ;

    //Pending durumundaki order in numarasi
    @FindBy (xpath = "(//h2[@class='text-xl text-left font-semibold'])[4]")
    public WebElement ordersNumberStatusDelivered ;

    //Siparislerin Status Durumlari
    @FindBy (xpath = "(//span[@class='text-orange-600'])[1]")
    public WebElement statusCanceled ;

    @FindBy (xpath = "(//span[@class='text-orange-600'])[2]")
    public WebElement statusPending ;

    @FindBy (xpath = "(//span[@class='text-orange-600'])[3]")
    public WebElement statusConfirmed ;

    @FindBy (xpath = "(//span[@class='text-orange-600'])[4]")
    public WebElement statusDelivered ;






















}