package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.CustomerManagementPage;
import snackattack.utilities.*;

public class CustomerManagementStepDef {

    HomePage homePage = new HomePage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    CustomerManagementPage customerManagementPage = new CustomerManagementPage();
    WebDriver driver = Driver.getDriver();



    @Then("Tum user listesinin goruntulendigini kontrol edilir")
    public void tumUserListesininGoruntulendiginiKontrolEdilir() {

        Assert.assertTrue(customerManagementPage.allUsersTableTitle.isDisplayed());
        Assert.assertTrue(customerManagementPage.usersTableHead.isDisplayed());
        Assert.assertTrue(customerManagementPage.usersTableBody.isDisplayed());


    }

    @When("Admin Customer Management user listesinden herhangi bir kaydin cop kutusu icon'ina tiklar")
    public void adminCustomerManagementListesindenHerhangiBirKaydinCopKutusuIconInaTiklar() {

        TestData.expectedUserId=customerManagementPage.firstRowUserId.getText();
        ReusableMethods.waitForSecond(2);
        customerManagementPage.firstRowDeleteIcon.click();


    }

    @Then("Silinmesi hedeflenilen userin listede olmadigi kontrol edilir")
    public void silinmesiHedeflenilenUserinListedeOlmadigiKontrolEdilir() {

        ReusableMethods.waitForSecond(2);
        driver.navigate().refresh();
        ReusableMethods.waitForSecond(2);
        Assert.assertNotEquals(customerManagementPage.firstRowUserId.getText(), TestData.expectedUserId);


    }

}

