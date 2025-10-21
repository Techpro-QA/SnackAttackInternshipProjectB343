package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.pages.HomePage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.JSUtils;
import snackattack.utilities.WaitUtils;

public class LoginStepDefs {
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
        String passwordValue;
        String configAttempt = ConfigReader.getProperty(passwordKey);
        // Ternary Operatörü ile passwordValue'yu belirliyoruz:
        // Koşul: Eğer configAttempt, null değilse, boş değilse VE orijinal passwordKey'den farklı bir değerse
        //       (yani ConfigReader gerçekten bir değer döndürdüyse).
        passwordValue = (configAttempt != null && !configAttempt.isEmpty() && !configAttempt.equals(passwordKey))
                ? configAttempt   // ConfigReader'dan gelen değeri kullan.
                : passwordKey;    // yoksa, doğrudan girilen String değeri kullan.

        WaitUtils.waitForVisibility(homePage.loginPasswordTextBox, 5);
        homePage.loginPasswordTextBox.clear();
        homePage.loginPasswordTextBox.sendKeys(passwordValue);

        String source = (configAttempt != null && !configAttempt.isEmpty() && !configAttempt.equals(passwordKey))
                ? "Config Dosyası"
                : "Doğrudan Girdi";
        System.out.println("Login için kullanılan parola gönderildi. Kaynak: " + source);
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
}
