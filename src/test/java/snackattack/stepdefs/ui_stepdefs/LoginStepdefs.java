package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.pages.HomePage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.JSUtils;
import snackattack.utilities.WaitUtils;

public class LoginStepdefs {
    HomePage homePage = new HomePage();
    @And("Kullanici loginButton'a tiklar")
    public void kullaniciLoginButtonATiklar() {
        homePage.loginButton.click();
    }

    @When("Kullanici login Email alanina {string} yazar")
    public void kullaniciLoginEmailAlaninaYazar(String emailKeyOrValue) {
        String emailValue = ConfigReader.getProperty(emailKeyOrValue);
        emailValue = (emailValue != null && !emailValue.isEmpty()) ? emailValue : emailKeyOrValue;

        WaitUtils.waitForVisibility(homePage.loginEmailTextBox, 5);
        homePage.loginEmailTextBox.clear();
        homePage.loginEmailTextBox.sendKeys(emailValue);
        System.out.println("Login icin kullanilan email: " + emailValue);
    }

    @And("Kullanici login Password alanina {string} yazar")
    public void kullaniciLoginPasswordAlaninaYazar(String passwordKey) {
        String passwordValue = ConfigReader.getProperty(passwordKey);
        String source = "Config Dosyası";

        if (passwordValue == null || passwordValue.isEmpty()) {
            passwordValue = passwordKey;
            source = "Doğrudan Girdi";
        }

        WaitUtils.waitForVisibility(homePage.loginPasswordTextBox, 5);
        homePage.loginPasswordTextBox.clear();
        homePage.loginPasswordTextBox.sendKeys(passwordValue);

        System.out.println("Login için parola gönderildi. Kaynak: " + source);
    }

    @Then("{string} mesaji email altinda gorunmeli")
    public void mesajiEmailAltindaGorunmeli(String expectedMessage) {
        WaitUtils.waitForVisibility(homePage.loginRequiredFieldErrorMessage,5);
        String actualMessage = homePage.loginRequiredFieldErrorMessage.getText().trim();
        System.out.println("Email altinda gorunen mesaj: " + actualMessage);
        Assert.assertTrue(actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()));

    }

    @When("Kullanici login Email alanini bos birakir")
    public void kullaniciLoginEmailAlaniniBosBirakir() {homePage.loginEmailTextBox.clear();}

    @And("Kullanici login Password alanini bos birakir")
    public void kullaniciLoginPasswordAlaniniBosBirakir() {homePage.loginPasswordTextBox.clear();}

    @Then("{string} mesaji password altinda gorunmeli")
    public void mesajiPasswordAltindaGorunmeli(String expectedMessage) {
        
    }

    @Then("{string} mesaji {string} altinda gorunmeli")
    public void mesajiAltindaGorunmeli(String expectedMessage, String fieldName) {
        WaitUtils.waitForVisibility(homePage.loginRequiredFieldErrorMessage, 5);
        String actualMessage = homePage.loginRequiredFieldErrorMessage.getText().trim();
        System.out.println(fieldName + " altinda gorunen mesaj: " + actualMessage);
        Assert.assertTrue(actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()));

    }


    @Then("Sayfada hata mesaji gorunur")
    public void sayfadaHataMesajiGorunur() {
        WaitUtils.waitForVisibility(homePage.authLoginErrorStatus,3);
        Assert.assertTrue(homePage.authLoginErrorStatus.isDisplayed());
        String text = homePage.authLoginErrorStatus.getText();
        System.out.println("Genel hata/status: " + text);
    }

    @Then("Uyari yada hata mesaji email altinda gorunmeli")
    public void uyariYadaHataMesajiEmailAltindaGorunmeli() {
        String actualMessage = JSUtils.getValidationMessage(homePage.loginEmailTextBox);
        String expectedKeyword = "işareti ekleyin";
        System.out.println("BEKLENEN MESAJ: " + expectedKeyword);
        System.out.println("GELEN GERÇEK MESAJ: " + actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedKeyword));

    }


    @When("Kullanici Forgot Password? linkine tiklar")
    public void kullaniciForgotPasswordLinkineTiklar() {
        WaitUtils.waitFor(2);
        homePage.forgotPasswordLink.click();
    }

    @Then("Kullanici sifre sifirlama sayfasina yonlendirildigini dogrular")
    public void kullaniciSifreSifirlamaSayfasinaYonlendirildiginiDogrular() {

        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/forgot-password"));
        System.out.println(" Kullanici sifre sifirlama sayfasina yonlendirildi: " + currentUrl);
    }
}
