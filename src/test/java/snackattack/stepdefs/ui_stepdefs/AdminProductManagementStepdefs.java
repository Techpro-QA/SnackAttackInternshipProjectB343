package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.ProductManagementPage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.JSUtils;

import java.util.List;

public class AdminProductManagementStepdefs {

    HomePage homePage = new HomePage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    ProductManagementPage productPage = new ProductManagementPage();
    WebDriver driver = Driver.getDriver();


    @And("Admin Customer Management'a tiklar")
    public void adminCustomerManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.customerManagementMenu);
        adminPanelPage.customerManagementMenu.click();
    }

    @And("Admin Admin Management'a tiklar")
    public void adminAdminManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.adminManagementMenu);
        adminPanelPage.adminManagementMenu.click();
    }

    @And("Admin Order Management'a tiklar")
    public void adminOrderManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.orderManagementMenu);
        adminPanelPage.orderManagementMenu.click();
    }


    @And("Admin Category Management'a tiklar")
    public void adminCategoryManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.categoryManagementMenu);
        adminPanelPage.categoryManagementMenu.click();
    }

    @And("Admin Payment Management'a tiklar")
    public void adminPaymentManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.paymentManagementMenu);
        adminPanelPage.paymentManagementMenu.click();
    }

    @And("Admin Add.Cat.Management'a tiklar")
    public void adminAddCatManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.addCatManagementMenu);
        adminPanelPage.addCatManagementMenu.click();
    }

    @And("Admin Support Requests'a tiklar")
    public void adminSupportRequestsATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.supportRequestsMenu);
        adminPanelPage.supportRequestsMenu.click();
    }

    @When("Admin Product Management'a tiklar")
    public void adminProductManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.productManagementMenu);
        adminPanelPage.productManagementMenu.click();
    }





}
