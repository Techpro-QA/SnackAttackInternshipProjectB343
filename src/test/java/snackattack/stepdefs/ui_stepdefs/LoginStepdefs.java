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
    @And("{string} loginButton'a tiklar")
    public void loginButtonaTiklar(String rol) {
        // Login butonuna tıklama işlemi
        WaitUtils.waitForVisibility(homePage.loginButton, 5);
        homePage.loginButton.click();

        System.out.println(rol + " login butonuna tıkladı.");
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


    @When("{string} Forgot Password linkine tiklar")
    public void forgotPasswordLinkineTiklar(String rol) {
        WaitUtils.waitFor(2);
        homePage.forgotPasswordLink.click();
        System.out.println(rol + " Forgot Password linkine tıkladı.");
    }


    @Then("{string} sifre sifirlama sayfasina yonlendirildigini dogrular")
    public void sifreSifirlamaSayfasinaYonlendirildiginiDogrular(String rol) {
        String currentUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue("Kullanici sifre sifirlama sayfasina yonlendirilmedi!",
                currentUrl.endsWith("/forgot-password"));

        System.out.println(rol + " sifre sifirlama sayfasina yonlendirildi: " + currentUrl);
    }

    @When("{string} login Email alanina {string} yazar")
    public void loginEmailAlaninaYazar(String rol, String emailKeyOrValue) {
        // email bilgisini config dosyasından çek veya doğrudan kullan
        String emailValue = ConfigReader.getProperty(emailKeyOrValue);
        emailValue = (emailValue != null && !emailValue.isEmpty()) ? emailValue : emailKeyOrValue;

        // hangi rolün giriş yaptığını consola yaz
        System.out.println(rol + " login Email alanina yazilan deger: " + emailValue);

        // textbox görünene kadar bekle
        WaitUtils.waitForVisibility(homePage.loginEmailTextBox, 5);

        // textbox'i temizle ve email degerini yaz
        homePage.loginEmailTextBox.clear();
        homePage.loginEmailTextBox.sendKeys(emailValue);
    }

    @And("{string} login Password alanina {string} yazar")
    public void loginPasswordAlaninaYazar(String rol, String passwordKeyOrValue) {
        // Şifreyi config dosyasından al veya doğrudan kullan
        String passwordValue = ConfigReader.getProperty(passwordKeyOrValue);
        String source = "Config Dosyası";

        if (passwordValue == null || passwordValue.isEmpty()) {
            passwordValue = passwordKeyOrValue;
            source = "Doğrudan Girdi";
        }

        // Şifre alanını görünür hale gelene kadar bekle
        WaitUtils.waitForVisibility(homePage.loginPasswordTextBox, 5);

        // Alanı temizle ve şifreyi gönder
        homePage.loginPasswordTextBox.clear();
        homePage.loginPasswordTextBox.sendKeys(passwordValue);

        System.out.println(rol + " için login parolası gönderildi. Kaynak: " + source);
    }

    @When("{string} login Email alanini bos birakir")
    public void loginEmailAlaniniBosBirakir(String rol) {
        WaitUtils.waitForVisibility(homePage.loginEmailTextBox, 5);
        homePage.loginEmailTextBox.clear();
        System.out.println(rol + " login Email alanini bos birakti.");
    }
    @And("{string} login Password alanini bos birakir")
    public void loginPasswordAlaniniBosBirakir(String rol) {
        WaitUtils.waitForVisibility(homePage.loginPasswordTextBox, 5);
        homePage.loginPasswordTextBox.clear();
        System.out.println(rol + " login Password alanini bos birakti.");
    }


}
