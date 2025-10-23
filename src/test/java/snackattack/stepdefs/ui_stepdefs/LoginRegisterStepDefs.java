package snackattack.stepdefs.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import snackattack.pages.HomePage;
import snackattack.utilities.*;

public class LoginRegisterStepDefs {

    HomePage homePage= new HomePage();
    // ===== Test Data =====
    private final Faker faker = new Faker();
    private String generatedPassword;
    private String randomEmail;

    @And("Kullanici First Name alanina gecerli data girer")
    public void kullaniciFirstNameAlaninaGecerliDataGirer() {
    String firstName = faker.name().firstName();
    TestData.firstName = firstName;//hafizaya kaydet
    homePage.registerFirstNameTextBox.sendKeys(firstName);

    }

    @And("Kullanici Last Name alanina gecerli data girer")
    public void kullaniciLastNameAlaninaGecerliDataGirer() {
        String lastName = faker.name().lastName();
        TestData.lastName = lastName; //hafizaya kaydet
        homePage.registerLastNameTextBox.sendKeys(lastName);
    }


    @And("Kullanici User Name alanina gecerli data girer")
    public void kullaniciUserNameAlaninaGecerliDataGirer() {
        String userName = faker.name().username();
        TestData.userName = userName;//hafizaya kaydet
        homePage.registerUserNameTextBox.sendKeys(userName);
    }


    @And("Kullanici Confirm Password alanina Password ile ayni degeri girer")
    public void kullaniciConfirmPasswordAlaninaPasswordIleAyniDegeriGirer() {
        WaitUtils.waitForVisibility(homePage.registerConfirmPasswordTextBox,3);
        Assert.assertNotNull("generated password yok; once Password adimini calistir", TestData.password);
        homePage.registerConfirmPasswordTextBox.clear();
        homePage.registerConfirmPasswordTextBox.sendKeys(TestData.password);

    }

    @And("Kullanici Address alanina gecerli data girer")
    public void kullaniciAddressAlaninaGecerliDataGirer() {
        String address = faker.address().fullAddress();
        TestData.address = address;
        homePage.registerAddressTextBox.sendKeys(address);
    }

    @And("Kullanici Country Code select alanindan {string} secer")
    public void kullaniciCountryCodeSelectAlanindanSecer(String countryCode) {
        JSUtils.JSscrollIntoView(homePage.registerCountryCodeSelect);
        Select select = new Select(homePage.registerCountryCodeSelect);
        select.selectByValue(countryCode);
        WaitUtils.waitFor(3);
        String selectedValue = select.getFirstSelectedOption().getAttribute("value");
        Assert.assertEquals(countryCode, selectedValue);

    }

    @And("Kullanici Phone Number alanina TR kuralina uygun {int} haneli numara girer")
    public void kullaniciPhoneNumberAlaninaTRKuralinaUygunHaneliNumaraGirer(int length) {
        WaitUtils.waitFor(3);
        int telDigits = length-1;
        String phone = "5"+ faker.number().digits(telDigits);
        TestData.phoneNumber = phone;
        homePage.registerPhoneNumberTextBox.sendKeys(phone);
    }

    @When("Kullanici register butonuna tiklar")
    public void kullaniciButonunaTiklar() {
        WaitUtils.waitForClickablility(homePage.registerSubmitButton,5);
        JSUtils.JSclickWithTimeout(homePage.registerSubmitButton);

    }

    @Then("Kullanici Anasayfaya yonlendigi dogrulanir")
    public void kayitIslemininBasariliOlduguMesajiIleDogrulanir( ) {
        WaitUtils.waitForVisibility(homePage.homeLogo,5);
        Assert.assertTrue(homePage.userPanelTitle.isDisplayed());
        String actualTitle = homePage.userPanelTitle.getText().trim();
        Assert.assertTrue(
                actualTitle.contains("Wellcome to Customer Panel")
        );
        System.out.println("Kullanici basariyla register olup, basariyla anasayfaya yonlenmistir");


    }


    @And("Kullanici kayit sayfasinda kalir ve URL {string} olarak devam eder")
    public void kullaniciKayitSayfasindaKalirVeURLOlarakDevamEder(String expectedPath) {
        WaitUtils.waitFor(2);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedPath));
        Assert.assertFalse(Driver.getDriver().getCurrentUrl().contains("/dashboard/member"));


    }

    @And("Kullanici First Name alanini bos birakir")
    public void kullaniciFirstNameAlaniniBosBirakir() {homePage.registerFirstNameTextBox.clear();}


    @Then("{string} alti uyari mesaji gorunur")
    public void altiUyariMesajiGorunur(String fieldName) {
        WaitUtils.waitFor(1);

        boolean isValidField =
                fieldName.equalsIgnoreCase("First Name")
                        || fieldName.equalsIgnoreCase("Last Name")
                        || fieldName.equalsIgnoreCase("User Name")
                        || fieldName.equalsIgnoreCase("Address")
                        || fieldName.equalsIgnoreCase("Email")
                        || fieldName.equalsIgnoreCase("Password")
                        || fieldName.equalsIgnoreCase("Confirm Password")
                        || fieldName.equalsIgnoreCase("Phone Number");

        Assert.assertTrue(
                "Bu step yalnizca First/Last/User Name/Address/Email/Password/Confirm Password icindir: " + fieldName,
                isValidField
        );

        String actual = homePage.requiredFieldErrorMessage.getText().trim();
        Assert.assertTrue(fieldName + " alti uyari gorunmedi!", actual.toLowerCase().contains("required"));
    }

    @And("Kullanici Last Name alanini bos birakir")
    public void kullaniciLastNameAlaniniBosBirakir() {homePage.registerLastNameTextBox.clear();}

    @And("Kullanici Email alanini bos birakir")
    public void kullaniciEmailAlaniniBosBirakir() {homePage.registerEmailTextBox.clear();}

    @And("Kullanici User Name alanini bos birakir")
    public void kullaniciUserNameAlaniniBosBirakir() {homePage.registerUserNameTextBox.clear();}

    @And("Kullanici Password alanini bos birakir")
    public void kullaniciPasswordAlaniniBosBirakir() {homePage.registerPasswordTextBox.clear();}

    @And("Kullanici Confirm Password alanini bos birakir")
    public void kullaniciConfirmPasswordAlaniniBosBirakir() {homePage.registerConfirmPasswordTextBox.clear();}

    @And("Kullanici registerButon'a tiklar")
    public void kullaniciRegisterButonATiklar() {
        ReusableMethods.visibleWait(homePage.registerButton,5);
        homePage.registerButton.click();
    }

    @And("Kullanici Password alanina {string} yazar")
    public void kullaniciPasswordAlaninaYazar(String key) {
        String passwordValue = (ConfigReader.getProperty(key) != null)
                ? ConfigReader.getProperty(key)
                : (key.equalsIgnoreCase("random") ? faker.internet().password(8,10)
                : (key.equalsIgnoreCase("memoryPassword") ? TestData.password : key));

        TestData.password = passwordValue; // <-- KRİTİK: her durumda hafızaya yaz
        homePage.registerPasswordTextBox.clear();
        homePage.registerPasswordTextBox.sendKeys(passwordValue);

        System.out.println("Kullanilan password: " + passwordValue);
    }

    @And("Kullanici Confirm Password alanina {string} yazar")
    public void kullaniciConfirmPasswordAlaninaYazar(String confirmPasswordKey) {
        String confirmPasswordValue = (ConfigReader.getProperty(confirmPasswordKey) != null)
                ? ConfigReader.getProperty(confirmPasswordKey)
                : (confirmPasswordKey.equalsIgnoreCase("memoryPassword")
                ? TestData.password
                : confirmPasswordKey);
        TestData.password = confirmPasswordValue;
        homePage.registerConfirmPasswordTextBox.clear();
        homePage.registerConfirmPasswordTextBox.sendKeys(confirmPasswordValue);
    }


    @And("Kullanici Address alanini bos birakir")
    public void kullaniciAddressAlaniniBosBirakir() {homePage.registerAddressTextBox.clear();}

    @And("Kullanici Phone Number alanini bos birakir")
    public void kullaniciPhoneNumberAlaniniBosBirakir() {homePage.registerPhoneNumberTextBox.clear();}

    @And("Kullanici Email alanina {string} yazar")
    public void kullaniciEmailAlaninaYazar(String emailKey) {
        String emailValue = (ConfigReader.getProperty(emailKey) != null)
                ? ConfigReader.getProperty(emailKey)
                : (emailKey.equalsIgnoreCase("random")
                ? (TestData.email = faker.internet().emailAddress())      //  kaydet
                : (emailKey.equalsIgnoreCase("memoryEmail")
                ? TestData.email                                     //  tekrar kullan
                : emailKey));

        homePage.registerEmailTextBox.clear();
        homePage.registerEmailTextBox.sendKeys(emailValue);

        System.out.println("Kullanilan email: " + emailValue);
    }


    @And("Kullanici Password alanina gecerli password yazar")
    public void kullaniciPasswordAlaninaGecerliPasswordYazar() {
        homePage.registerPasswordTextBox.sendKeys(ConfigReader.getProperty("userPassword"));
    }

    @And("Kullanici Confirm Password alanina gecerli password yazar")
    public void kullaniciConfirmPasswordAlaninaGecerliPasswordYazar() {
        homePage.registerConfirmPasswordTextBox.sendKeys(ConfigReader.getProperty("userPassword"));
    }


    @Then("Password alti {string} uyarisi gorunur")
    public void passwordAltiUyarisiGorunur(String expected) {
        WaitUtils.waitFor(2);
        String actual = homePage.password4CharactersErrorMessage.getText().trim();
        Assert.assertTrue(
                actual.equalsIgnoreCase(expected) || actual.contains(expected)
        );
    }

    @Then("Confirm Password alti {string} uyarisi gorunur")
    public void confirmPasswordAltiUyarisiGorunur(String expected) {
        WaitUtils.waitFor(2);
        String actual = homePage.passwordsMustMatchMessage.getText().trim();
        Assert.assertTrue(
                actual.equalsIgnoreCase(expected) || actual.contains(expected)
        );
    }

    @Then("Email alti {string} uyarisi gorunur")
    public void emailAltiUyarisiGorunur(String expected) {
        WaitUtils.waitFor(2);
        String actual = homePage.invalidEmailMessage.getText().trim();
        Assert.assertTrue(
                actual.equalsIgnoreCase(expected) || actual.contains(expected)
        );
    }

    @Then("Kullanici sayfada {string} mesaji gormeli")
    public void kullaniciSayfadaMesajiGormeli(String expectedText) {
        WebElement elementToVerify;

        elementToVerify = (expectedText.toLowerCase().contains("token") || expectedText.toLowerCase().contains("read"))
                ? homePage.registerStatusMessagePopup         // Pop-up hata (Örn: Unexpected token)
                : homePage.authLoginErrorStatus;   //  hata (Örn: Required)

        WaitUtils.waitForVisibility(elementToVerify, 10);
        String actualText = elementToVerify.getText()
                .trim()
                .toLowerCase();

        String cleanExpectedText = expectedText.trim().toLowerCase();
        System.out.println("TEST EDİLECEK BEKLENEN METİN: " + expectedText);
        System.out.println("SEÇİLEN ELEMENTİN METNİ: " + actualText);
        Assert.assertTrue(actualText.contains(cleanExpectedText));

    }

    @And("Kullanici Phone Number alanina harf veya ozel karakter icerikli {string} yazar")
    public void kullaniciPhoneNumberAlaninaHarfVeyaOzelKarakterIcerikliYazar(String phone) {
        WaitUtils.waitForVisibility(homePage.registerPhoneNumberTextBox,3);
        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phone);
    }

    @Then("Phone Number alti {string} uyarisi gorunur")
    public void phoneNumberAltiUyarisiGorunur(String expected) {
        WaitUtils.waitFor(2);
        String actual= homePage.registerInvalidPhoneNumberMessage.getText().trim();
        Assert.assertTrue(
                actual.equalsIgnoreCase(expected) || actual.toLowerCase().contains(expected.toLowerCase())
        );
    }

    @And("Kullanici Phone Number alanina ulke formatina uymayan sekilde {string} yazar")
    public void kullaniciPhoneNumberAlaninaUlkeFormatinaUymayanSekildeYazar(String phone) {
        WaitUtils.waitForVisibility(homePage.registerPhoneNumberTextBox, 3);
        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phone);
    }


    @And("Kullanici Phone Number alanina {string} girer")
    public void kullaniciPhoneNumberAlaninaGirer(String phoneKey) {
        WaitUtils.waitForVisibility(homePage.registerPhoneNumberTextBox,2);
        String phoneValue = (ConfigReader.getProperty(phoneKey) != null)
                ? ConfigReader.getProperty(phoneKey)
                : phoneKey; // config dosyasinda yoksa yazdigimi kullanacak

        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phoneValue);

    }


    @Then("Kullanici login sayfasina yonlenir ve URL {string} olarak devam eder")
    public void kullaniciLoginSayfasinaYonlenirVeURLOlarakDevamEder(String expectedPath) {
        WaitUtils.waitFor(3);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedPath));
        System.out.println("Kullanici login sayfasina yonlendirildi: " + currentUrl);
    }
}
