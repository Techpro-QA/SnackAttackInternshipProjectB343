package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.PaymentManagementPage;
import snackattack.utilities.Driver;
import snackattack.utilities.ReusableMethods;
import snackattack.utilities.TestData;

import java.util.List;

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


        for (WebElement row : paymentManagementPage.tableRows) {

            row.click();
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
        String firstRowPaymentIdText = paymentManagementPage.firstRowPaymentId.getText();
        TestData.expectedFirstRowPaymentId=firstRowPaymentIdText;

        String firstRowOrderIdText = paymentManagementPage.firstRowOrderId.getText();
        TestData.expectedFirstRowOrderId=firstRowOrderIdText;

        String firstRowUserIdText = paymentManagementPage.firstRowUserId.getText();
        TestData.expectedFirstRowUserId=firstRowUserIdText;

        String firstRowAmountText = paymentManagementPage.firstRowAmount.getText();
        TestData.expectedFirstRowAmount=firstRowAmountText;

        String firstRowPaymentDateText = paymentManagementPage.firstRowPaymentDate.getText();
        TestData.expectedFirstRowPaymentDate=firstRowPaymentDateText;



    }

    @When("Eger ikinci sayfa aktifse Admin ikinci sayfaya gider ve ödeme detaylarını görüntüler")
    public void egerIkinciSayfaAktifseAdminIkinciSayfayaGiderVeÖdemeDetaylarınıGörüntüler() {

        ReusableMethods.visibleWait(paymentManagementPage.secondPageButton, 10);

        // ikinci sayfa varsa
        if (paymentManagementPage.secondPageButton.isDisplayed()) {
            paymentManagementPage.secondPageButton.click();
            ReusableMethods.visibleWait(paymentManagementPage.secondPageButton,10);

            for (WebElement row : paymentManagementPage.tableRows) {

                row.click();
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
        }else {
            System.out.println("All Payment sadece bir sayfadan ibaretdir");
        }
    }
    @And("Eger ücüncü sayfa aktifse Admin ücüncü sayfaya gider ve ödeme detaylarını görüntüler")
    public void egerÜcüncüSayfaAktifseAdminÜcüncüSayfayaGiderVeÖdemeDetaylarınıGörüntüler() {
        ReusableMethods.visibleWait(paymentManagementPage.thirdPageButton, 10);

        // ücüncü sayfa varsa
        if (paymentManagementPage.thirdPageButton.isDisplayed()) {
            paymentManagementPage.thirdPageButton.click();
            ReusableMethods.visibleWait(paymentManagementPage.secondPageButton,10);

            for (WebElement row : paymentManagementPage.tableRows) {

                row.click();
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
        }else {
            System.out.println("All Payment sadece iki sayfadan ibaretdir");
        }
    }

}