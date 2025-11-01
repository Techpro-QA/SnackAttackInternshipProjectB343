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

public class LoginRegisterStepdefs {

    HomePage homePage= new HomePage();
    // ===== Test Data =====
    private final Faker faker = new Faker();


    @And("{string} First Name alanina gecerli data girer")
    public void firstNameAlaninaGecerliDataGirer(String rol) {
        String firstName = faker.name().firstName();
        TestData.firstName = firstName;//hafizaya kaydet
        homePage.registerFirstNameTextBox.sendKeys(firstName);
    }


    @And("{string} Last Name alanina gecerli data girer")
    public void lastNameAlaninaGecerliDataGirer(String rol) {
        String lastName = faker.name().lastName();
        TestData.lastName = lastName;
        homePage.registerLastNameTextBox.sendKeys(lastName);
    }


    @And("{string} User Name alanina gecerli data girer")
    public void userNameAlaninaGecerliDataGirer(String rol) {
        String userName = faker.name().username();
        TestData.userName = userName;
        homePage.registerUserNameTextBox.sendKeys(userName);
    }

    @And("{string} Password alanina {string} yazar")
    public void passwordAlaninaYazar(String rol, String key) {
        String passwordValue = (ConfigReader.getProperty(key) != null)
                ? ConfigReader.getProperty(key)
                : (key.equalsIgnoreCase("random") ? faker.internet().password(8,10)
                : (key.equalsIgnoreCase("memoryPassword") ? TestData.password : key));

        TestData.password = passwordValue;
        homePage.registerPasswordTextBox.clear();
        homePage.registerPasswordTextBox.sendKeys(passwordValue);
        System.out.println(rol + " icin kullanilan password: " + passwordValue);
    }

    @And("{string} Confirm Password alanina Password ile ayni degeri girer")
    public void confirmPasswordAlaninaPasswordIleAyniDegeriGirer(String rol) {
        WaitUtils.waitForVisibility(homePage.registerConfirmPasswordTextBox,3);
        Assert.assertNotNull("generated password yok; once Password adimini calistir", TestData.password);
        homePage.registerConfirmPasswordTextBox.clear();
        homePage.registerConfirmPasswordTextBox.sendKeys(TestData.password);
    }

    @And("{string} Address alanina gecerli data girer")
    public void addressAlaninaGecerliDataGirer(String rol) {
        String address = faker.address().fullAddress();
        TestData.address = address;
        homePage.registerAddressTextBox.sendKeys(address);
    }

    @And("{string} Country Code select alanindan {string} secer")
    public void countryCodeSelectAlanindanSecer(String rol, String countryCode) {
        JSUtils.JSscrollIntoView(homePage.registerCountryCodeSelect);
        Select select = new Select(homePage.registerCountryCodeSelect);
        select.selectByValue(countryCode);
        WaitUtils.waitFor(3);
        String selectedValue = select.getFirstSelectedOption().getAttribute("value");
        Assert.assertEquals(countryCode, selectedValue);
    }

    @And("{string} Phone Number alanina TR kuralina uygun {int} haneli numara girer")
    public void phoneNumberAlaninaTRKuralinaUygunHaneliNumaraGirer(String rol, int length) {
        WaitUtils.waitFor(1);
        String[] validPrefixes = {"530","535", "540", "545"};
        int idx = faker.number().numberBetween(0, validPrefixes.length);
        String prefix = validPrefixes[idx];
        int remainingDigits = length - prefix.length();
        String suffix = faker.number().digits(remainingDigits);
        String phone = prefix + suffix;
        TestData.phoneNumber = phone;
        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phone);
        System.out.println(rol + " icin girilen telefon numarasi: " + phone);
    }

    @When("{string} register butonuna tiklar")
    public void registerButonunaTiklar(String rol) {
        WaitUtils.waitForClickablility(homePage.registerSubmitButton,5);
        JSUtils.JSclickWithTimeout(homePage.registerSubmitButton);
    }

    @Then("{string} login sayfasina yonlenir ve URL {string} olarak devam eder")
    public void loginSayfasinaYonlenirVeURLOlarakDevamEder(String rol, String expectedPath) {
        WaitUtils.waitFor(3);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedPath));
        System.out.println(rol + " login sayfasina yonlendirildi: " + currentUrl);
    }

    @Then("{string} Anasayfaya yonlendigi dogrulanir")
    public void anasayfayaYonlendigiDogrulanir(String rol) {
        WaitUtils.waitForVisibility(homePage.homeLogo,5);
        Assert.assertTrue(homePage.userPanelTitle.isDisplayed());
        String actualTitle = homePage.userPanelTitle.getText().trim();
        Assert.assertTrue(actualTitle.contains("Wellcome to Customer Panel"));
        System.out.println(rol + " basariyla register olup anasayfaya yonlendirildi");
    }

    @And("{string} kayit sayfasinda kalir ve URL {string} olarak devam eder")
    public void kayitSayfasindaKalirVeURLOlarakDevamEder(String rol, String expectedPath) {
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

    @And("{string} Last Name alanini bos birakir")
    public void lastNameAlaniniBosBirakir(String rol) {
        homePage.registerLastNameTextBox.clear();
        System.out.println(rol + " Last Name alanini bos birakti");
    }

    @And("{string} Email alanini bos birakir")
    public void emailAlaniniBosBirakir(String rol) {
        homePage.registerEmailTextBox.clear();
        System.out.println(rol + " Email alanini bos birakti");
    }

    @And("{string} User Name alanini bos birakir")
    public void userNameAlaniniBosBirakir(String rol) {
        homePage.registerUserNameTextBox.clear();
        System.out.println(rol + " User Name alanini bos birakti");
    }

    @And("{string} Password alanini bos birakir")
    public void passwordAlaniniBosBirakir(String rol) {
        homePage.registerPasswordTextBox.clear();
        System.out.println(rol + " Password alanini bos birakti");
    }

    @And("{string} Confirm Password alanini bos birakir")
    public void confirmPasswordAlaniniBosBirakir(String rol) {
        homePage.registerConfirmPasswordTextBox.clear();
        System.out.println(rol + " Confirm Password alanini bos birakti");
    }

    @And("{string} registerButon'a tiklar")
    public void registerButonATiklar(String rol) {
        ReusableMethods.visibleWait(homePage.registerButton, 5);
        homePage.registerButton.click();
        System.out.println(rol + " register butonuna tikladi");
    }


    @And("{string} Confirm Password alanina {string} yazar")
    public void confirmPasswordAlaninaYazar(String rol, String confirmPasswordKey) {
        String confirmPasswordValue = (ConfigReader.getProperty(confirmPasswordKey) != null)
                ? ConfigReader.getProperty(confirmPasswordKey)
                : (confirmPasswordKey.equalsIgnoreCase("memoryPassword")
                ? TestData.password
                : confirmPasswordKey);

        TestData.password = confirmPasswordValue;
        homePage.registerConfirmPasswordTextBox.clear();
        homePage.registerConfirmPasswordTextBox.sendKeys(confirmPasswordValue);
        System.out.println(rol + " Confirm Password alanina '" + confirmPasswordValue + "' yazdi");
    }

    @And("{string} Address alanini bos birakir")
    public void addressAlaniniBosBirakir(String rol) {
        homePage.registerAddressTextBox.clear();
        System.out.println(rol + " Address alanini bos birakti");
    }

    @And("{string} Phone Number alanini bos birakir")
    public void phoneNumberAlaniniBosBirakir(String rol) {
        homePage.registerPhoneNumberTextBox.clear();
        System.out.println(rol + " Phone Number alanini bos birakti");
    }

    @And("{string} Email alanina {string} yazar")
    public void emailAlaninaYazar(String rol, String emailKey) {
        String emailValue = (ConfigReader.getProperty(emailKey) != null)
                ? ConfigReader.getProperty(emailKey)
                : (emailKey.equalsIgnoreCase("random")
                ? (TestData.email = faker.internet().emailAddress())      // yeni random email oluştur
                : (emailKey.equalsIgnoreCase("memoryEmail")
                ? TestData.email                                         // hafızadaki email’i yeniden kullan
                : emailKey));                                            // direkt verilen değeri yaz

        homePage.registerEmailTextBox.clear();
        homePage.registerEmailTextBox.sendKeys(emailValue);
        System.out.println(rol + " Email alanina '" + emailValue + "' yazdi");
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

    @Then("{string} sayfada {string} mesaji gormeli")
    public void sayfadaMesajiGormeli(String rol, String expectedText) {
        WebElement elementToVerify;

        // mesaj turune gore karsilastirilacak elementi seciyoruz
        elementToVerify = (expectedText.toLowerCase().contains("token") || expectedText.toLowerCase().contains("read"))
                ? homePage.registerStatusMessagePopup       // Pop-up hata (örnek: Unexpected token)
                : homePage.authLoginErrorStatus;            // Giriş hatası (örnek: Required)

        //elementin gorunmesini bekliyoruz
        WaitUtils.waitForVisibility(elementToVerify, 10);

        // metinleri karsilastirabilmek icin formlarini ayni yapiyoruz
        String actualText = elementToVerify.getText().trim().toLowerCase();
        String cleanExpectedText = expectedText.trim().toLowerCase();

        System.out.println(rol + " için TEST EDİLECEK BEKLENEN METİN: " + expectedText);
        System.out.println("SEÇİLEN ELEMENTİN GERÇEK METNİ: " + actualText);

        // Assertions
        Assert.assertTrue(actualText.contains(cleanExpectedText));
    }


    @And("{string} Phone Number alanina harf veya ozel karakter icerikli {string} yazar")
    public void phoneNumberAlaninaHarfVeyaOzelKarakterIcerikliYazar(String rol, String phone) {
        WaitUtils.waitForVisibility(homePage.registerPhoneNumberTextBox, 3);
        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phone);
        System.out.println(rol + " Phone Number alanina harf/ozel karakter icerikli '" + phone + "' yazdi");
    }


    @Then("Phone Number alti {string} uyarisi gorunur")
    public void phoneNumberAltiUyarisiGorunur(String expected) {
        WaitUtils.waitFor(2);
        String actual= homePage.registerInvalidPhoneNumberMessage.getText().trim();
        Assert.assertTrue(
                actual.equalsIgnoreCase(expected) || actual.toLowerCase().contains(expected.toLowerCase())
        );
    }

    @And("{string} Phone Number alanina ulke formatina uymayan sekilde {string} yazar")
    public void phoneNumberAlaninaUlkeFormatinaUymayanSekildeYazar(String rol, String phone) {
        WaitUtils.waitForVisibility(homePage.registerPhoneNumberTextBox, 3);
        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phone);

        System.out.println(rol + " Phone Number alanina ulke formatina uymayan '" + phone + "' yazdi");
    }



    @And("{string} Phone Number alanina {string} girer")
    public void phoneNumberAlaninaGirer(String rol, String phoneKey) {
        WaitUtils.waitForVisibility(homePage.registerPhoneNumberTextBox, 2);

        String phoneValue = (ConfigReader.getProperty(phoneKey) != null)
                ? ConfigReader.getProperty(phoneKey)
                : phoneKey; // config dosyasinda yoksa yazdigini kullanir

        homePage.registerPhoneNumberTextBox.clear();
        homePage.registerPhoneNumberTextBox.sendKeys(phoneValue);

        System.out.println(rol + " Phone Number alanina '" + phoneValue + "' girdi");
    }

    @And("{string} First Name alanini bos birakir")
    public void firstNameAlaniniBosBirakir(String rol) {
        homePage.registerFirstNameTextBox.clear();
        System.out.println(rol + "FirstName alanini bos birakti");
    }
}
