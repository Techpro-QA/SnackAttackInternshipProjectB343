package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.PaymentManagementPage;
import snackattack.utilities.ReusableMethods;

import static org.junit.Assert.assertTrue;

public class PaymentManagementStepdefs {
    PaymentManagementPage paymentManagementPage = new PaymentManagementPage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();



    @Then("Payment Management görüntülenir")
    public void paymentManagementGörüntülenir() {
        assertTrue(paymentManagementPage.allPaymentsText.isDisplayed());
    }

    @Then("Payment ID, Order ID, User ID, Amount ve Payment Date sutunlarin altında veriler goruntulenir")
    public void paymentIDOrderIDUserIDAmountVePaymentDateSutunlarinAltındaVerilerGoruntulenir() {

        for (WebElement header : paymentManagementPage.tableHeaders) {
            assertTrue(header.getText() + " görünmüyor!", header.isDisplayed());
        }
    }

    @And("Admin görüntülemek istedigi herhangi bir ödeme satirina tiklar ve Order Code, Address, Payment Method, Status ve Product bilgileri eksiksiz goruntulenir")
    public void adminGörüntülemekIstedigiHerhangiBirÖdemeSatirinaTiklar() {


        for (WebElement row1 : paymentManagementPage.tableRows) {

            row1.click();
            ReusableMethods.visibleWait(paymentManagementPage.orderDetailsText, 10);
            assertTrue(paymentManagementPage.orderCodeText.isDisplayed());
            ReusableMethods.visibleWait(paymentManagementPage.orderDetailsText, 10);
            assertTrue(paymentManagementPage.addressText.isDisplayed());
            ReusableMethods.visibleWait(paymentManagementPage.orderDetailsText, 10);
            assertTrue(paymentManagementPage.paymentMethodText.isDisplayed());
            ReusableMethods.visibleWait(paymentManagementPage.orderDetailsText, 10);
            assertTrue(paymentManagementPage.statusText.isDisplayed());
            ReusableMethods.visibleWait(paymentManagementPage.orderDetailsText, 10);
            paymentManagementPage.closeButton.click();


        }
    }

}