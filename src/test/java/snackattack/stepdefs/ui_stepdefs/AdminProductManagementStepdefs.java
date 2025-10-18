package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.ProductManagementPage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.JSUtils;

import java.util.List;

public class AdminProductManagementStepdefs {

    HomePage homePage = new HomePage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    ProductManagementPage productPage = new ProductManagementPage();
    WebDriver driver = Driver.getDriver();

    @Given("Sayfaya gidilir")
    public void sayfayaGidilir() {
        Driver.getDriver().get(ConfigReader.getProperty("snackUrl"));
    }

    @And("Kullanici loginRegister'a tiklar")
    public void kullaniciLoginRegisterATiklar() {
        homePage.homeLoginRegisterButton.click();
    }

    @And("Kullanici {string} olarak giriş yapar")
    public void kullaniciOlarakGirişYapar(String name) {

        String email = name.equals("admin") ? ConfigReader.getProperty("adminEmail") : ConfigReader.getProperty("userEmail");
        String password = name.equals("admin") ? ConfigReader.getProperty("adminPassword") : ConfigReader.getProperty("userPassword");

        homePage.loginEmailTextBox.sendKeys(email);
        homePage.loginPasswordTextBox.sendKeys(password);
        homePage.loginButton.click();
    }

    @When("Admin Product Management'a tiklar")
    public void adminProductManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.productManagementMenu);
        adminPanelPage.productManagementMenu.click();
    }

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
        Assert.assertFalse("Arama sonucu mesajı görünmüyor!" + expectedMessage, productPage.noProductFoundMessage.isDisplayed());

    }






}
