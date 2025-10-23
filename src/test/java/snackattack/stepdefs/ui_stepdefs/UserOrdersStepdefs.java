package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import snackattack.pages.userpanelpages.MyOrdersPage;
import snackattack.utilities.Driver;

import java.time.Duration;

public class UserOrdersStepdefs {

    MyOrdersPage ordersPage = new MyOrdersPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

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


}


