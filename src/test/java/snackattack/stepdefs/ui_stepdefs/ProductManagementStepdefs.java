package snackattack.stepdefs.ui_stepdefs;

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
import snackattack.utilities.Driver;

import java.util.List;


public class ProductManagementStepdefs {
    ProductManagementPage productManagementPage = new ProductManagementPage();
    HomePage homePage = new HomePage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    ProductManagementPage productPage = new ProductManagementPage();
    WebDriver driver = Driver.getDriver();

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

        if(productManagementPage.firstProductRow.isDisplayed()) {
            productManagementPage.firstProductRow.click();
        }

    }




}
