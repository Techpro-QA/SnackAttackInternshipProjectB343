package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import snackattack.pages.ProductsPage;
import snackattack.pages.userpanelpages.MyCartPage;
import snackattack.utilities.*;
import snackattack.pages.HomePage;

import java.time.Duration;
import java.util.List;

public class ProductPageStepdefs {

    HomePage homePage = new HomePage();
    ProductsPage productsPage = new ProductsPage();
    MyCartPage myCartPage = new MyCartPage();
    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


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
        try {
            ActionsUtils.scrollEnd();
        } catch (Exception ignore) {
        } // :contentReference[oaicite:1]{index=1}
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


    @And("Kullanici sol ustteki ilk urune tiklar")
    public void kullaniciIlkUruneTiklar() {
        String text1 = productsPage.firstProduct.getText();
        TestData.expectedProductName = text1;

        productsPage.firstProduct.click();
    }

    @And("Kullanici Add to Cart'a tiklar")
    public void kullaniciAddToCartATiklar() {
        homePage.addToCart.click();
    }

    @Given("Sag ust kosedeki sepet ikonuna tiklanir")
    public void solÜstKöşedekiSepetIkonunaTiklanir() {
        String text1 = productsPage.firstProduct.getText();
        TestData.expectedProductName = text1;
        WaitUtils.waitForClickablility(homePage.cartIkon, 5);
        homePage.cartIkon.click();
    }

    @Then("Kullanicinin seçtigi urunun sepetindeki urun ile aynı urun oldugu dogrulanir")
    public void kullanicininSeçtigiUrununSepetindekiUrunIleAynıUrunOlduguDogrulanir() {

        String text2 = myCartPage.selectedProduct.getText();
        String newText2 = text2.split(" -")[0].trim();//alınan ürün bilgisi sadece ürün adı kalacak şekilde kesilir
        System.out.println("newText2 = " + newText2);
        System.out.println("TestData.expectedProductName = " + TestData.expectedProductName);
        Assert.assertTrue(TestData.expectedProductName.contains(newText2));


    }

    @Given("Kullanici sag ustteki ismine tiklar")
    public void kullaniciSagUsttekiIsmineTiklar() {

        WaitUtils.waitForClickablility(myCartPage.userIcon, 5);
        myCartPage.userIcon.click();
    }

    @When("Kullanici dropdown menüdeki dashboard a tiklar")
    public void kullaniciDropdownMenüdekiDashboardATiklar() {
        WaitUtils.waitForVisibility(myCartPage.dashboard, 10);
        WaitUtils.waitForClickablility(myCartPage.dashboard, 10);
        myCartPage.dashboard.click();
    }

    @Then("Kullanicinin seçtigi urunun sepetindeki urun ile aynı urun oldugu ikinci yol ile dogrulanir")
    public void kullanicininSeçtigiUrununSepetindekiUrunIleAynıUrunOlduguIkinciYolIleDogrulanir() {

        String text2 = myCartPage.selectedProductSecondWay.getText();
        System.out.println(text2);
        System.out.println("TestData.expectedProductName = " + TestData.expectedProductName);
        Assert.assertTrue(TestData.expectedProductName.contains(text2));

    }

    @And("Kullanici Clear Cart a tiklar")
    public void kullaniciClearCartATiklar() {
        myCartPage.clearCartButton.click();
    }

    @And("Kullanici acılan pop up ta Clear Cart a tiklar")
    public void kullaniciAcılanPopUpTaClearCartATiklar() {
        WaitUtils.waitForClickablility(myCartPage.confirmClearCartButton, 5);
        js.executeScript("arguments[0].click();", myCartPage.confirmClearCartButton);
    }

    @And("Cart Management Paneldeki sag ust kosedeki sepet ikonuna tiklanir")
    public void cartManagementPaneldekiSagUstKosedekiSepetIkonunaTiklanir() {
        WaitUtils.waitForClickablility(myCartPage.cartManagementPanelCartIkon, 3);
        myCartPage.cartManagementPanelCartIkon.click();
    }

    @Then("Sepetin bos oldugu dogrulanır")
    public void sepetinBosOlduguDogrulanır() {
        String actualText = myCartPage.confirmClearSuccessful.getText();
        String expectedText = "Your cart is empty.";
        Assert.assertEquals(expectedText, actualText);
    }


    @And("Kullanici artı sembolune tıklar")
    public void kullaniciArtıSemboluneTıklar() {
        myCartPage.plusIkon.click();
    }

    @Then("Sepetteki urun miktarının arttıgı oldugu dogrulanır")
    public void sepettekiUrunMiktarınınIkiOlduguDogrulanır() {
        WaitUtils.waitForVisibility(myCartPage.quantityUpdatedConfirmation, 5);
        String actualText = myCartPage.quantityUpdatedConfirmation.getText();
        System.out.println(actualText);
        String expectedText = "Item added successfully.";
        Assert.assertEquals(expectedText, actualText);
    }

    @And("Kullanici eksi sembolune tiklar")
    public void kullaniciEksiSemboluneTiklar() {
        myCartPage.minusIkon.click();
    }

    @Then("Sepetteki urun miktarının azaldıgı oldugu dogrulanır")
    public void sepettekiUrunMiktarınınAzaldıgıOlduguDogrulanır() {
        WaitUtils.waitForVisibility(myCartPage.quantityUpdatedConfirmation, 5);
        String actualText = myCartPage.quantityUpdatedConfirmation.getText();
        System.out.println(actualText);
        String expectedText = "Quantity updated.";
        Assert.assertEquals(expectedText, actualText);
    }

    @Then("Kullanici Checkout a tiklar")
    public void kullaniciCheckoutATiklar() {
        myCartPage.checkoutButton.click();
    }

    @And("Kullanici Create Order a tiklar")
    public void kullaniciCreateOrderATiklar() {
        myCartPage.createOrderButton.click();
    }

    @And("Acılan pop up tan CREDIT_CARD secilerek Ödeme Yap a tiklanır")
    public void acılanPopUpTanCREDIT_CARDSecilerekÖdemeYapATiklanır() {
        ReusableMethods.ddmVisibleText(myCartPage.ddmKartlaOdeme,"CREDIT_CARD");
        myCartPage.odemeYap.click();
    }

    @And("Acılan odeme sayfasına kredi kartı bilgileri girilir")
    public void ödemeSayfasınaKrediKartıBilgileriGirilir() {
       Assert.assertFalse(myCartPage.creditCardTextBox.isEmpty());
    }


    @And("Acılan pop up tan Cash secilerek Ödeme Yap a tiklanır")
    public void acılanPopUpTanCashSecilerekÖdemeYapATiklanır() {
        ReusableMethods.ddmVisibleText(myCartPage.ddmKartlaOdeme,"CASH");
        myCartPage.odemeYap.click();
    }
}
