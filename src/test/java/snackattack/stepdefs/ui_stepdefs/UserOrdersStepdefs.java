package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import snackattack.pages.userpanelpages.MyCartPage;
import snackattack.pages.userpanelpages.MyOrdersPage;
import snackattack.utilities.Driver;
import snackattack.utilities.ReusableMethods;

import java.time.Duration;

import static snackattack.utilities.ReusableMethods.alertText;

public class UserOrdersStepdefs {

    MyOrdersPage ordersPage = new MyOrdersPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    MyCartPage myCartPage = new MyCartPage();

    @Then("En az bir siparis karti gorunur")
    public void enAzBirSiparisKartiGorunur() {
        wait.until(d -> !ordersPage.myOrdersMenu.isEmpty());
        int count = ordersPage.myOrdersMenu.size();
        System.out.println("Kart sayisi: " + count);
        Assert.assertTrue("Siparis karti bulunamadi!", count > 0);
    }

    @And("Her siparis kartinda zorunlu alanlar gecerli olur")
    public void herSiparisKartindaZorunluAlanlarGecerliOlur() {
        // 1) En az bir kart geldi mi?
        wait.until(d -> !ordersPage.myOrdersMenu.isEmpty());
        Assert.assertTrue("Siparis karti yok!", ordersPage.myOrdersMenu.size() > 0);

        int validated = 0;
        // 2) sabit locator çiftleriyle kontrol(orders number + status)
        validated += validateOrderAndStatusPair(ordersPage.ordersNumberStatusDelivered, ordersPage.statusDelivered, "DELIVERED");
        validated += validateOrderAndStatusPair(ordersPage.ordersNumberStatusCanceled,  ordersPage.statusCanceled,  "CANCELED");
        validated += validateOrderAndStatusPair(ordersPage.ordersNumberStatusConfirmed,ordersPage.statusConfirmed,"CONFIRMED");
        validated += validateOrderAndStatusPair(ordersPage.ordersNumberStatusPending,  ordersPage.statusPending,  "PENDING");

        // 3) En az bir çift doğrulanmış olmalı (sayfada her zaman 4 statü birden olmayabilir)
        Assert.assertTrue("Hiçbir siparis numarasi + status cifti dogrulanamadi!", validated > 0);

        System.out.println("✅ Zorunlu alan dogrulamalari tamam. Dogrulanan cift sayisi: " + validated);
    }

    /* ---------- Yardimcilar ---------- */

    private int validateOrderAndStatusPair(WebElement orderNumberEl, WebElement statusEl, String expectedStatus) {
        try {
            // element gerçekten var mı/gorunur mu?
            if (!isVisible(orderNumberEl) || !isVisible(statusEl)) return 0;

            String orderNoText = orderNumberEl.getText().trim();      // "Order #56"
            String statusText  = statusEl.getText().trim().toUpperCase(); // "PENDING" vb.

            // Order # rakam içeriyor mu?
            String digits = orderNoText.replaceAll("\\D+", "");
            Assert.assertFalse("Order numarasi bos/hatali: '" + orderNoText + "'", digits.isEmpty());

            // Status beklenen setten mi? (expectedStatus veriyorsun)
            Assert.assertTrue("Status beklenen degil! Beklenen: " + expectedStatus + " | Gercek: " + statusText,
                    statusText.contains(expectedStatus));

            System.out.printf("✓ %s -> Order #%s%n", statusText, digits);
            return 1;
        } catch (Exception e) {
            // görünmüyorsa veya bulunamazsa bu çifti atla (test fail olmasin, diger ciftlere bakiyoruz)
            return 0;
        }
    }

    private boolean isVisible(WebElement el) {
        try {
            wait.until(ExpectedConditions.visibilityOf(el));
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    @Then("{string} durumlu en az bir siparis gorur")
    public void durumluEnAzBirSiparisGorur(String expectedStatus) {
        String exp = expectedStatus.trim().toUpperCase();
        System.out.println("🔍 Kontrol ediliyor: " + exp + " durumlu siparis...");

        boolean found = false;

        switch (exp) {
            case "DELIVERED":
                found = checkStatusPair(ordersPage.ordersNumberStatusDelivered, ordersPage.statusDelivered, "DELIVERED");
                break;

            case "CANCELED":
            case "CANCELLED": // bazen 2 “L” ile yazilabilir
                found = checkStatusPair(ordersPage.ordersNumberStatusCanceled, ordersPage.statusCanceled, "CANCELED");
                break;

            case "CONFIRMED":
                found = checkStatusPair(ordersPage.ordersNumberStatusConfirmed, ordersPage.statusConfirmed, "CONFIRMED");
                break;

            case "PENDING":
                found = checkStatusPair(ordersPage.ordersNumberStatusPending, ordersPage.statusPending, "PENDING");
                break;

            default:
                Assert.fail("⚠️ Desteklenmeyen status girdisi: " + exp);
        }

        Assert.assertTrue("❌ " + exp + " durumlu siparis bulunamadi!", found);
        System.out.println("✅ " + exp + " durumlu siparis bulundu ve dogrulandi.");
    }

    /* ----------------- Yardimci ----------------- */

    private boolean checkStatusPair(WebElement orderNoEl, WebElement statusEl, String expected) {
        try {
            wait.until(ExpectedConditions.visibilityOf(statusEl));

            if (!statusEl.isDisplayed() || !orderNoEl.isDisplayed()) return false;

            String orderText = orderNoEl.getText().trim();
            String statusText = statusEl.getText().trim().toUpperCase();

            // Order numarası boş olamaz
            String digits = orderText.replaceAll("\\D+", "");
            Assert.assertFalse("Order numarasi bos/hatali: " + orderText, digits.isEmpty());

            // Status beklenen mi
            Assert.assertTrue("Status beklenen degil! Beklenen: " + expected + " | Gercek: " + statusText,
                    statusText.contains(expected));

            System.out.printf("✓ Status %s icin Order #%s dogrulandi.%n", expected, digits);
            return true;

        } catch (Exception e) {
            System.out.println("❗ " + expected + " kontrolunde element gorunmedi veya hata: " + e.getMessage());
            return false;
        }
    }

    @Then("Your Orders sayfasında status pending oldugu dogrulanır")
    public void yourOrdersSayfasındaStatusPendingOlduguDogrulanır() {
        myCartPage.okButton.click();
        String text = myCartPage.statusElement.getText();
        System.out.println("text = " + text);
        Assert.assertTrue(text.contains("PENDING"));
    }


    @And("Your Orders sayfasında Cancel Order a tiklanir")
    public void yourOrdersSayfasındaCancelOrderATiklanır() {
        ordersPage.cancelOrderButton.click();
    }

    @And("Cıkan Are you sure you want to cancel this order? allertte tamama tiklanir")
    public void cıkanAllertTeTamamATiklanır() {
        ReusableMethods.alertWait(2);
        ReusableMethods.alertAccept();
    }

    @And("Cıkan Order cancelled successfully yazan alertte tamama tiklanir")
    public void cıkanOrderCancelledSuccessfullyYazanAlertteTamamaTiklanir() {
        ReusableMethods.alertWait(3);
        ReusableMethods.alertAccept();
    }

    @Then("Your Orders sayfasında status cancelled oldugu dogrulanır")
    public void yourOrdersSayfasındaStatusCancelledOlduguDogrulanır() {
        String text = myCartPage.statusElement.getText();
        System.out.println("text = " + text);
        Assert.assertTrue(text.contains("CANCELED"));
    }

    @And("Sayfa yenilenir")
    public void sayfaYenilenir() {
        Driver.getDriver().navigate().refresh();
    }

    @Then("Failed to cancel the order. alertunun acıldıgı dogrulanır")
    public void failedToCancelTheOrderAlertununCıktığıDogrulanır() {
        ReusableMethods.alertWait(2);
        String actual = ReusableMethods.alertTextString();
        System.out.println("Text = " + actual);
        Assert.assertEquals("Failed to cancel the order.", actual);
    }
}


