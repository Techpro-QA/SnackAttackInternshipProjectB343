package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import snackattack.pages.ProductsPage;
import snackattack.utilities.ActionsUtils;
import snackattack.utilities.Driver;
import snackattack.pages.HomePage;

import java.time.Duration;
import java.util.List;

public class ProductPageStepdefs {

    HomePage homePage = new HomePage();
    ProductsPage productsPage = new ProductsPage();
    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // ======================
    // TC_01
    // ======================
    @And("İlk ürün kartında ürün adı alanı dogrulanir.")
    public void ilkUrunKartindaUrunAdiAlaniDogrulanir() {
        // Sadece "Tatli 3333" metnini doğrula
        wait.until(ExpectedConditions.visibilityOf(productsPage.productNameTatli3333));
        String actual = productsPage.productNameTatli3333.getText().trim();
        Assert.assertEquals("İlk ürün adı beklenen değil!", "Tatli 3333", actual);
    }

    // ======================
    // TC_02
    // ======================
    @And("Sonraki butonuna tıklanır.")
    public void sonrakiButonunaTiklanir() {
        int before = getActivePageNumber();
        goToBottom();
        clickNextSafely();
        // aktif sayfa +1 olana kadar bekle
        wait.until(d -> getActivePageNumber() == before + 1);
    }

    @And("Son sayfaya kadar sonraki butonuna tıklanır")
    public void sonSayfayaKadarSonrakiButonunaTiklanir() {
        while (!isNextDisabled()) {
            int before = getActivePageNumber();
            goToBottom();
            clickNextSafely();
            wait.until(d -> getActivePageNumber() == before + 1);
        }
    }

    @Then("URL ve aktif sayfa göstergesi dogrulanir.")
    public void urlVeAktifSayfaGostergesiDogrulanir() {
        // Son sayfada Next disabled olmalı
        Assert.assertTrue("Son sayfada 'Next' devre dışı olmalı.", isNextDisabled());

        // URL page parametresi varsa aktif sayfayla uyuşsun
        String url = driver.getCurrentUrl();
        if (url.contains("page=")) {
            String pageStr = url.replaceAll(".*[?&]page=(\\d+).*", "$1");
            int urlPage = Integer.parseInt(pageStr);
            int active = getActivePageNumber();
            Assert.assertEquals("URL’deki 'page' aktif sayfa - 1 olmalı.",
                    urlPage, active - 1);
        }
    }

    // ===== Helper'lar =====
    private void goToBottom() {
        try { ActionsUtils.scrollEnd(); } catch (Exception ignore) {} // :contentReference[oaicite:1]{index=1}
    }

    private void clickNextSafely() {
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.nextButton));
        try {
            productsPage.nextButton.click();
        } catch (Exception e) {
            // Intercept vb. durumlarda JS fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsPage.nextButton);
        }
    }

    private int getActivePageNumber() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productsPage.activePageIndicator));
            String txt = productsPage.activePageIndicator.getText().trim();
            return Integer.parseInt(txt.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 1; // fallback
        }
    }

    private boolean isNextDisabled() {
        try {
            String clazz = productsPage.nextLi.getAttribute("class");
            return clazz != null && clazz.toLowerCase().contains("disabled");
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    // --- TC_03: Ürün adının kısmi eşleşmesi ile arama sonuç verir. ---

    private String lastSearchTerm; // step'ler arası kullanacağız

    @And("Kullanici Arama alanina {string} yazar.")
    public void kullaniciAramaAlaninaYazar(String term) {
        lastSearchTerm = term; // arama tetiklemede ve beklemede kullanacağız
        wait.until(ExpectedConditions.visibilityOf(productsPage.searchInput));
        productsPage.searchInput.click();
        productsPage.searchInput.clear();
        productsPage.searchInput.sendKeys(term);
    }

    @And("Kullanici aramayı tetikler.")
    public void kullaniciAramayiTetikler() {
        // Önce butonla dene, olmazsa Enter fallback
        try {
            wait.until(ExpectedConditions.elementToBeClickable(productsPage.searchButton)).click();
        } catch (Exception ignored) {
            // Buton lokasyonu farklı/etkileşim dışı ise Enter ile tetikle
            ActionsUtils.pressEnter(); // util’inizden; Enter gönderir :contentReference[oaicite:1]{index=1}
        }

    }

    @And("Sonuçlardaki ürün adlarında {string} kontrol edilir.")
    public void sonuclardakiUrunAdlarindaKontrolEdilir(String substring) {
        String q = substring.toLowerCase();

        // Görünen sayfada en az 1 ürün olmalı
        List<WebElement> titles = productsPage.productTitles;
        Assert.assertFalse("Arama sonucunda ürün bulunamadı.", titles.isEmpty());

        // Tüm görünen sonuçların adları substring içermeli (case-insensitive)
        for (WebElement el : titles) {
            String name = el.getText();
            Assert.assertTrue(
                    "Ürün adı beklenen parçayı içermiyor. Aranan: " + substring + " | Ad: " + name,
                    name != null && name.toLowerCase().contains(q)
            );
        }
    }
}
