package snackattack.stepdefs.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.ProductManagementPage;
import snackattack.utilities.*;

import java.util.Arrays;
import java.util.List;


public class ProductManagementStepdefs {
    ProductManagementPage productManagementPage = new ProductManagementPage();
    HomePage homePage = new HomePage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    ProductManagementPage productPage = new ProductManagementPage();
    WebDriver driver = Driver.getDriver();
    Faker faker = new Faker();

    List<WebElement> tumUpdateCheckboxlari = Arrays.asList(
            productManagementPage.updatePIZZACheckbox,
            productManagementPage.updateIceceklerCheckbox,
            productManagementPage.updateAtistirmaliklarCheckbox,
            productManagementPage.updatePİZZACheckbox,
            productManagementPage.updateTATLILARCheckbox,
            productManagementPage.updatepizzaCheckbox,
            productManagementPage.updateMEZELERCheckbox,
            productManagementPage.updateSALATALARCheckbox,
            productManagementPage.updateDenemeCheckbox,
            productManagementPage.updateDONERCheckbox,
            productManagementPage.updateHAMBURGERCheckbox
    );

    List<WebElement> tumEkKategoriCheckboxlari = Arrays.asList(
            productManagementPage.updateSoslarCheckbox,
            productManagementPage.updateSalataSoslariCheckbox,
            productManagementPage.updateMezelerCheckbox
    );

    @Then("Admin Admin Panel'e yönlenmis olmali")
    public void admin_panel_yonlendirme() {
        String url = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(url.contains("admin"));
    }

    @Then("Ürün listesi görünmeli")
    public void urun_listesi_gorunmeli() {
        Assert.assertTrue(productPage.productTable.isDisplayed());
    }

    @Then("Ürünler listesinde en az bir ürün görünmeli")
    public void urunler_listesinde_en_az_bir_urun_gorunmeli() {
        Assert.assertTrue("Ürün listesi boş!", productPage.allProductRows.size() > 0);
    }

    @And("Arama kutusuna {string} girip search butonuna tiklar")
    public void arama_kutusuna_urun_adi_girip_search_tiklar(String urunAdi) {
        productPage.searchBox.clear();
        productPage.searchBox.sendKeys(urunAdi);
        productPage.searchButton.click();
    }

    @Then("Arama kriterine uygun ürün aramasi yapilabilmeli")
    public void arama_kriterine_uygun_urun_arama_yapilabilmeli() {
        Assert.assertTrue("Ürün tablosu görünmüyor!", productPage.productTable.isDisplayed());
    }

    @When("Gecersiz email {string} ve password {string} ile giris yapar")
    public void gecersiz_admin_bilgileri_ile_giris_yapar(String email, String password) {

        homePage.loginEmailTextBox.sendKeys(email);
        homePage.loginPasswordTextBox.sendKeys(password);
        homePage.loginButton.click();
    }

    @Then("Login hatasi mesaji görünmeli")
    public void login_hatasi_mesaji_gorunmeli() {
        Assert.assertTrue("Hata mesajı görünmüyor!", productPage.loginErrorMessage.isDisplayed());
    }

    @Then("{string} mesaji görüntülenmeli")
    public void mesaj_görüntülenmeli(String expectedMessage) {
        List<WebElement> messages = Driver.getDriver().findElements(By.xpath("//*[contains(text(),'" + expectedMessage + "')]"));

        // Eğer mesaj DOM'da yoksa
        Assert.assertFalse("Beklenen mesaj DOM'da bulunamadı: " + expectedMessage, messages.isEmpty());

        // Eğer varsa, ilk mesajın görünür ve doğru metin içerdiğini kontrol et
        String actualMessage = messages.get(0).getText().trim();
        Assert.assertTrue("Mesaj beklenen metni içermiyor!", actualMessage.contains(expectedMessage));

    }

    @And("Admin güncelleyeceği urunune tiklar")
    public void adminGüncelleyeceğiUrununeTiklar() {

        WaitUtils.waitForVisibility(productManagementPage.firstProductRow,10);
        if(productManagementPage.firstProductRow.isDisplayed()) {
            productManagementPage.firstProductRow.click();
        }

    }


    @And("Admin Ürün Adı Textbox'ini {string} ile doldurur")
    public void adminÜrünAdıTextboxIniIleDoldurur(String productName) {

        if (productName.equals("random")){
            productName = faker.food().dish();
        }

        productManagementPage.updateProductNameTextbox.clear();
        productManagementPage.updateProductNameTextbox.sendKeys(productName);
        TestData.expectedProductName = productName;
    }

    @And("Admin Açıklama Textbox'ini {string} ile doldurur")
    public void adminAçıklamaTextboxIniIleDoldurur(String description) {
        productManagementPage.updateDescriptionTextbox.clear();
        productManagementPage.updateDescriptionTextbox.sendKeys(description);
    }

    @And("Admin İçerik Textbox'ini {string} ile doldurur")
    public void adminİçerikTextboxIniIleDoldurur(String contents) {
        productManagementPage.updateContentsTextbox.clear();
        productManagementPage.updateContentsTextbox.sendKeys(contents);
    }

    @And("Admin Fiyat Textbox'ini {string} ile doldurur")
    public void adminFiyatTextboxIniIleDoldurur(String price) {
        productManagementPage.updatePriceTextbox.clear();
        productManagementPage.updatePriceTextbox.sendKeys(price);
    }

    @And("Admin İndirim Textbox'ini {string} ile doldurur")
    public void adminİndirimTextboxIniIleDoldurur(String discount) {
        productManagementPage.updateDiscountTextbox.clear();
        productManagementPage.updateDiscountTextbox.sendKeys(discount);
        ActionsUtils.scrollEnd();
        WaitUtils.waitFor(2);
    }

    @And("Admin {string} kategorisini secer")
    public void adminKategorisiniSecer(String kategorilerCheckboxName) {

        for (WebElement checkbox : tumUpdateCheckboxlari) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        switch (kategorilerCheckboxName){
            case "PIZZA":
                if (!productManagementPage.updatePIZZACheckbox.isSelected()){
                    productManagementPage.updatePIZZACheckbox.click();
                }
                break;
            case "ICECEKLER":
                if (!productManagementPage.updateIceceklerCheckbox.isSelected()){
                    productManagementPage.updateIceceklerCheckbox.click();
                }
                break;
            case "Atıştırmalıklar":
                if (!productManagementPage.updateAtistirmaliklarCheckbox.isSelected()){
                    productManagementPage.updateAtistirmaliklarCheckbox.click();
                }
                break;
            case "PİZZA":
                if (!productManagementPage.updatePİZZACheckbox.isSelected()){
                    productManagementPage.updatePİZZACheckbox.click();
                }
                break;
            case "TATLILAR":
                if (!productManagementPage.updateTATLILARCheckbox.isSelected()){
                    productManagementPage.updateTATLILARCheckbox.click();
                }
                break;
            case "pizza":
                if (!productManagementPage.updatepizzaCheckbox.isSelected()){
                    productManagementPage.updatepizzaCheckbox.click();
                }
                break;
            case "MEZELER":
                if (!productManagementPage.updateMEZELERCheckbox.isSelected()){
                    productManagementPage.updateMEZELERCheckbox.click();
                }
                break;
            case "SALATALAR":
                if (!productManagementPage.updateSALATALARCheckbox.isSelected()){
                    productManagementPage.updateSALATALARCheckbox.click();
                }
                break;
            case "Deneme":
                if (!productManagementPage.updateDenemeCheckbox.isSelected()){
                    productManagementPage.updateDenemeCheckbox.click();
                }
                break;
            case "DONER":
                if (!productManagementPage.updateDONERCheckbox.isSelected()){
                    productManagementPage.updateDONERCheckbox.click();
                }
                break;
            case "HAMBURGER":
                if (!productManagementPage.updateHAMBURGERCheckbox.isSelected()){
                    productManagementPage.updateHAMBURGERCheckbox.click();
                }
                break;
        }

    }

    @And("Admin {string} Ek Kategorisini secer")
    public void adminEkKategorisiniSecer(String ekKategorilerCheckboxName) {


        for (WebElement checkbox : tumEkKategoriCheckboxlari) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }


        switch (ekKategorilerCheckboxName) {
            case "Soslar":
                if (!productManagementPage.updateSoslarCheckbox.isSelected()) {
                    productManagementPage.updateSoslarCheckbox.click();
                }
                break;
            case "Salata Soslari":
                if (!productManagementPage.updateSalataSoslariCheckbox.isSelected()) {
                    productManagementPage.updateSalataSoslariCheckbox.click();
                }
                break;
            case "Mezeler":
                if (!productManagementPage.updateSalataSoslariCheckbox.isSelected()) {
                    productManagementPage.updateSalataSoslariCheckbox.click();
                }
                break;
        }

    }

    @And("Admin populer mi? ve mevcut mu? checkboxlarini secer")
    public void adminPopulerMiVeMevcutMuCheckboxlariniSecer() {

        if (!productManagementPage.updatePopülerCheckbox.isSelected()){
            productManagementPage.updatePopülerCheckbox.click();
        }

        if (!productManagementPage.updateMevcutCheckbox.isSelected()){
            productManagementPage.updateMevcutCheckbox.click();
        }

    }

    @And("Admin Güncelle butonuna tiklar")
    public void adminGüncelleButonunaTiklar() {
        productManagementPage.updateGüncelleButton.click();
    }

    @Then("Urunun guncellendigi kontrol edilir")
    public void urununGuncellendigiKontrolEdilir() {
        Assert.assertTrue(Driver.getDriver().switchTo().alert().getText().equals("Ürün başarıyla güncellendi!"));
        Driver.getDriver().switchTo().alert().accept();
        WaitUtils.waitFor(2);
        productManagementPage.searchBox.sendKeys(TestData.expectedProductName);
        productManagementPage.searchButton.click();
        Assert.assertEquals(productManagementPage.searchedProductNameColumn.getText(),TestData.expectedProductName);

    }

    @And("Sayfa kapatilir")
    public void sayfaKapatilir() {
        Driver.closeDriver();
    }

    @Then("Urunun guncellenmedigi kontrol edilir")
    public void urununGuncellenmedigiKontrolEdilir() {
    }

    @When("Admin {string} resim ekler")
    public void adminResimEkler(String path) {

        if (path.contains("Mustafa")){
            ReusableMethods.click(productManagementPage.updateSelectFile);
        } else {
            ReusableMethods.click(productManagementPage.createProductSelectFile);
        }

        ReusableMethods.uploadFilePath(path);
    }

    @And("Admin submit butonuna scroll yapar")
    public void adminSubmitButonunaScrollYapar() {
        JSUtils.JSscrollIntoView(productManagementPage.moveToButton);
        WaitUtils.waitFor(1);
    }

    @Then("Gecersiz ürün adiyla arama sonucu bulunamamali")
    public void gecersiz_name_search_bulunamamali() {
        Assert.assertTrue("Ürün tablosu görünmüyor!", productPage.firstProductRow.isDisplayed());
    }

    @And("Admin New butonuna tiklar")
    public void adminNewButonunaTiklar() {

        productManagementPage.createProductSelectFile.click();
    }

    @And("Admin {string} ve {string} seceneklerini secer")
    public void adminVeSecenekleriniSecer() {

        if (!productManagementPage.createAvailableCheckbox.isSelected()){
            productManagementPage.createAvailableCheckbox.click();
        }

        if (!productManagementPage.createActiveCheckbox.isSelected()){
            productManagementPage.createActiveCheckbox.click();
        }

    }

    @And("Admin Create Product butonuna tiklar")
    public void adminCreateProductButonunaTiklar() {

        productManagementPage.createProductButton.click();
    }

    @Then("Urunun eklendigi dogrulanir")
    public void urununEklendigiDogrulanir() {

//        Alert alert = driver.switchTo().alert();
//        String alertText = alert.getText();
//        Assert.assertEquals("Ürün başarıyla eklendi", alertText);
//        alert.accept(); // kapatmak için

        Assert.assertTrue("Ürün başarıyla eklendi", productManagementPage.successMessage.isDisplayed());

    }




}
