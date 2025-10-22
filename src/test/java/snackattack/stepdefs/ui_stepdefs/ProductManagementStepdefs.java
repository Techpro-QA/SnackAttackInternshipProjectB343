package snackattack.stepdefs.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.ProductManagementPage;
import snackattack.utilities.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


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
            productName = faker.food().dish()+ "_" + UUID.randomUUID().toString().substring(0, 4);
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
        WaitUtils.waitFor(2);
    }

    @And("Admin {string} kategorisini secer")
    public void adminKategorisiniSecer(String kategorilerCheckboxName) {

        for (WebElement checkbox : tumUpdateCheckboxlari) {
            if (checkbox.isSelected()) {
                ReusableMethods.click(checkbox);
            }
        }

        switch (kategorilerCheckboxName){
            case "PIZZA":
                if (!productManagementPage.updatePIZZACheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updatePIZZACheckbox);
                }
                break;
            case "ICECEKLER":
                if (!productManagementPage.updateIceceklerCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateIceceklerCheckbox);
                }
                break;
            case "Atıştırmalıklar":
                if (!productManagementPage.updateAtistirmaliklarCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateAtistirmaliklarCheckbox);
                }
                break;
            case "PİZZA":
                if (!productManagementPage.updatePİZZACheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updatePİZZACheckbox);
                }
                break;
            case "TATLILAR":
                if (!productManagementPage.updateTATLILARCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateTATLILARCheckbox);
                }
                break;
            case "pizza":
                if (!productManagementPage.updatepizzaCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updatepizzaCheckbox);
                }
                break;
            case "MEZELER":
                if (!productManagementPage.updateMEZELERCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateMEZELERCheckbox);
                }
                break;
            case "SALATALAR":
                if (!productManagementPage.updateSALATALARCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateSALATALARCheckbox);
                }
                break;
            case "Deneme":
                if (!productManagementPage.updateDenemeCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateDenemeCheckbox);
                }
                break;
            case "DONER":
                if (!productManagementPage.updateDONERCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateDONERCheckbox);
                }
                break;
            case "HAMBURGER":
                if (!productManagementPage.updateHAMBURGERCheckbox.isSelected()){
                    ReusableMethods.click(productManagementPage.updateHAMBURGERCheckbox);
                }
                break;
        }

    }

    @And("Admin {string} Ek Kategorisini secer")
    public void adminEkKategorisiniSecer(String ekKategorilerCheckboxName) {


        for (WebElement checkbox : tumEkKategoriCheckboxlari) {
            if (checkbox.isSelected()) {
                ReusableMethods.click(checkbox);
            }
        }


        switch (ekKategorilerCheckboxName) {
            case "Soslar":
                if (!productManagementPage.updateSoslarCheckbox.isSelected()) {
                    ReusableMethods.click(productManagementPage.updateSoslarCheckbox);
                }
                break;
            case "Salata Soslari":
                if (!productManagementPage.updateSalataSoslariCheckbox.isSelected()) {
                    ReusableMethods.click(productManagementPage.updateSalataSoslariCheckbox);
                }
                break;
            case "Mezeler":
                if (!productManagementPage.updateMezelerCheckbox.isSelected()) {
                    ReusableMethods.click(productManagementPage.updateMezelerCheckbox);
                }
                break;
        }

    }

    @And("Admin populer mi? ve mevcut mu? checkboxlarini secer")
    public void adminPopulerMiVeMevcutMuCheckboxlariniSecer() {

        if (!productManagementPage.updatePopülerCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.updatePopülerCheckbox);
        }

        if (!productManagementPage.updateMevcutCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.updateMevcutCheckbox);
        }

    }

    @And("Admin Güncelle butonuna tiklar")
    public void adminGüncelleButonunaTiklar() {
        ReusableMethods.click(productManagementPage.updateGüncelleButton);
        WaitUtils.waitFor(2);
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

        ReusableMethods.checkValidationMessage(Driver.getDriver(),productManagementPage.updateProductNameTextbox,"Lütfen bu alanı doldurun.");

    }

    @When("Admin {string} resim ekler")
    public void adminResimEkler(String path) {

        File file = new File(path);

        if (path.contains("Mustafa")){
            productManagementPage.updateSelectFile.sendKeys(file.getAbsolutePath());
        } else {
            productManagementPage.createProductSelectFile.sendKeys(file.getAbsolutePath());
        }

        ReusableMethods.uploadFilePath(path);
    }

    @And("Admin submit butonuna scroll yapar")
    public void adminSubmitButonunaScrollYapar() {
        JSUtils.JSscrollIntoView(productManagementPage.moveToButton);
        WaitUtils.waitFor(4);
    }

    @Then("Gecersiz ürün adiyla arama sonucu bulunamamali")
    public void gecersiz_name_search_bulunamamali() {
        Assert.assertTrue("Ürün tablosu görünmüyor!", productPage.firstProductRow.isDisplayed());
    }

    @And("Admin New butonuna tiklar")
    public void adminNewButonunaTiklar() {

        productManagementPage.newProductButton.click();
    }

    @And("Admin Available ve Active seceneklerini secer")
    public void adminAvailableActiveSecenekleriniSecer() {

        if (!productManagementPage.createAvailableCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.createAvailableCheckbox);
        }

        if (!productManagementPage.createActiveCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.createActiveCheckbox);
        }

    }

    @And("Admin Create Product butonuna tiklar")
    public void adminCreateProductButonunaTiklar() {

        ReusableMethods.click(productManagementPage.createProductButton);
    }

    @Then("Urunun eklendigi dogrulanir")
    public void urununEklendigiDogrulanir() {

        System.out.println(TestData.expectedProductName);
        WaitUtils.waitFor(2);
        Driver.getDriver().switchTo().alert().accept(); //ürün eklendi bildirimi
        WaitUtils.waitFor(2);
        productManagementPage.searchBox.sendKeys(TestData.expectedProductName);
        ReusableMethods.click(productManagementPage.searchButton);
        WaitUtils.waitFor(2);

        String actualProductName = productManagementPage.searchedProductNameColumn.getText().trim();
        String expectedProductName = TestData.expectedProductName.trim();

        System.out.println("Expected: " + expectedProductName);
        System.out.println("Actual  : " + actualProductName);

        Assert.assertEquals(expectedProductName, actualProductName);

    }
    @Then("Urunun eklenemedigi dogrulanir")
    public void urununEklenemedigiDogrulanir() {

        String validationMessage = productManagementPage.updateProductNameTextbox.getAttribute("validationMessage");
        Assert.assertEquals("Lütfen bu alanı doldurun.",validationMessage);

    }

    @Then("Urun eklenememeli")
    public void urunEklenememeli() {

        // Önce eklenen ürün adını al (random'du)
        String expectedProductName = TestData.expectedProductName;
        System.out.println("Kontrol edilecek ürün adı: " + expectedProductName);

        WaitUtils.waitFor(3);
        // Alert varsa kapat (ürün hatalı şekilde eklendiği için alert çıkabilir)
        try {
            Alert alert = Driver.getDriver().switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Gelen Alert Mesajı: " + alertText);
            alert.accept();
            WaitUtils.waitFor(2);
        } catch (NoAlertPresentException e) {
            System.out.println("Alert bulunamadı.");
        }

        // Ürün arama işlemi
        productManagementPage.searchBox.clear();
        productManagementPage.searchBox.sendKeys(TestData.expectedProductName);
        productManagementPage.searchButton.click();
        WaitUtils.waitFor(2);

        // searchedProductNameColumn tek bir WebElement'se:
        boolean isProductFound;

        try {
            isProductFound = productManagementPage.searchedProductNameColumn.isDisplayed() &&
                    productManagementPage.searchedProductNameColumn.getText().equals(TestData.expectedProductName);
        } catch (NoSuchElementException e) {
            isProductFound = false;
        }

        Assert.assertFalse("Ürün eklenmemeliydi ama listede bulundu!", isProductFound);

    }

    @Then("Zorunlu alanlari doldurma dogrulanir")
    public void zorunluAlanlariDoldurmaDogrulanir() {

        WaitUtils.waitFor(2);

            Alert alert = Driver.getDriver().switchTo().alert();  // Alert'e geç
            String alertText = alert.getText();                   // Alert mesajını al
            System.out.println("Alert mesajı: " + alertText);

            // Assert: Alert mesajı beklendiği gibi mi?
            Assert.assertTrue(alertText.contains("zorunlu") || alertText.contains("doldurun"));

            alert.accept(); // Alert'i kapat

        WaitUtils.waitFor(2);


    }

    @And("Admin Available ve Active seceneklerini secmez")
    public void adminAvailableVeActiveSecenekleriniSecmez() {

        if (productManagementPage.createAvailableCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.createAvailableCheckbox);
        }

        if (productManagementPage.createActiveCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.createActiveCheckbox);
        }
    }


    @And("Admin silmek istedigi urunun Actions kismindaki çop kutusu\\(delete) icon'una tiklar")
    public void adminSilmekIstedigiUrununActionsKismindakiÇopKutusuDeleteIconUnaTiklar() {

        TestData.expectedProductName = productManagementPage.firstProductName.getText();
        ReusableMethods.click(productManagementPage.productActionsDeleteButton);
        WaitUtils.waitFor(2);


    }

    @And("Admin Are you sure you want to delete this product? alertini görür ve Tamam'a tiklar")
    public void adminAreYouSureYouWantToDeleteThisProductAlertiniGörürVeTamamATiklar() {
        WaitUtils.waitFor(2);
        Assert.assertTrue(Driver.getDriver().switchTo().alert().getText().contains("Are you sure you want to delete this product?"));
        ReusableMethods.alertAccept();
        WaitUtils.waitFor(2);

    }

    @And("Admin mevcut mu? checkbox'indaki tiki kaldirir\\(unsellected)")
    public void adminMevcutMuCheckboxIndakiTikiKaldirirUnsellected() {

        if (productManagementPage.updateMevcutCheckbox.isSelected()){
            ReusableMethods.click(productManagementPage.updateMevcutCheckbox);
        }
    }

    @Then("Urunun silindigi kontrol edilir")
    public void urununSilindigiKontrolEdilir() {
        Assert.assertTrue(Driver.getDriver().switchTo().alert().getText().contains("Ürün başarıyla güncellendi!"));
        ReusableMethods.alertAccept();
        WaitUtils.waitFor(2);
        productManagementPage.searchBox.sendKeys(TestData.expectedProductName);
        productManagementPage.searchButton.click();
        Assert.assertEquals(1,productManagementPage.searchedProductsList.size());
    }

    @Then("Urunun negatif verilerle guncellenmedigi kontrol edilir")
    public void urununNegatifVerilerleGuncellenmedigiKontrolEdilir() {
        Assert.assertTrue(Driver.getDriver().switchTo().alert().getText().contains("Lütfen tüm zorunlu alanları doldurun!"));
    }
}
