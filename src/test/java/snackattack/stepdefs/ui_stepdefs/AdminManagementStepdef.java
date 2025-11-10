package snackattack.stepdefs.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.AdminManagementPage;
import snackattack.pages.adminpanelpages.ProductManagementPage;
import snackattack.utilities.*;

public class AdminManagementStepdef {

    HomePage homePage = new HomePage();
    AdminManagementPage adminManagementPage = new AdminManagementPage();
    Faker faker = new Faker();


    @When("New Buttonuna tiklar")
    public void newButtonunaTiklar() {

        adminManagementPage.adminManagementNewButton.click();
    }


    @And("Admin First Name alanina gecerli data girer")
    public void adminFirstNameAlaninaGecerliDataGirer() {
        String firstName = faker.name().firstName();
        TestData.firstName = firstName;
        homePage.registerFirstNameTextBox.sendKeys(firstName);

    }

    @And("Admin Last Name alanina gecerli data girer")
    public void adminLastNameAlaninaGecerliDataGirer() {
        String lastName = faker.name().lastName();
        TestData.lastName = lastName; //hafizaya kaydet
        homePage.registerLastNameTextBox.sendKeys(lastName);
    }

    @And("Admin Email alanina {string} yazar")
    public void adminEmailAlaninaYazar(String emailKey) {
        String emailValue = (ConfigReader.getProperty(emailKey) != null)
                ? ConfigReader.getProperty(emailKey)
                : (emailKey.equalsIgnoreCase("random")
                ? (TestData.email = faker.internet().emailAddress())
                : (emailKey.equalsIgnoreCase("memoryEmail")
                ? TestData.email
                : emailKey));

        homePage.registerEmailTextBox.clear();
        homePage.registerEmailTextBox.sendKeys(emailValue);

        System.out.println("Kullanilan email: " + emailValue);
    }


    @And("Admin User Name alanina gecerli data girer")
    public void adminUserNameAlaninaGecerliDataGirer() {
        String userName = faker.name().username();
        TestData.userName = userName;
        homePage.registerUserNameTextBox.sendKeys(userName);
    }

    @And("Admin Password alanina {string} yazar")
    public void adminPasswordAlaninaYazar(String key) {
        String passwordValue = (ConfigReader.getProperty(key) != null)
                ? ConfigReader.getProperty(key)
                : (key.equalsIgnoreCase("random")
                ? (TestData.password = faker.internet().password(8, 10))
                : (key.equalsIgnoreCase("memoryPassword")
                ? TestData.password
                : key));

        homePage.registerPasswordTextBox.clear();
        homePage.registerPasswordTextBox.sendKeys(passwordValue);

        System.out.println("Kullanilan password: " + passwordValue);
    }

    @And("Admin Confirm Password alanina Password ile ayni degeri girer")
    public void adminConfirmPasswordAlaninaPasswordIleAyniDegeriGirer() {
        WaitUtils.waitForVisibility(homePage.registerConfirmPasswordTextBox, 3);
        Assert.assertNotNull("generated password yok; once Password adimini calistir", TestData.password);
        homePage.registerConfirmPasswordTextBox.clear();
        homePage.registerConfirmPasswordTextBox.sendKeys(TestData.password);

    }


    @And("Admin Address alanina gecerli data girer")
    public void adminAddressAlaninaGecerliDataGirer() {
        String address = faker.address().fullAddress();
        TestData.address = address;
        homePage.registerAddressTextBox.sendKeys(address);
    }

    @And("Admin Country Code select alanindan {string} secer")
    public void adminCountryCodeSelectAlanindanSecer(String countryCode) {
        JSUtils.JSscrollIntoView(homePage.registerCountryCodeSelect);
        Select select = new Select(homePage.registerCountryCodeSelect);
        select.selectByValue(countryCode);
        WaitUtils.waitFor(3);
        String selectedValue = select.getFirstSelectedOption().getAttribute("value");
        Assert.assertEquals(countryCode, selectedValue);

    }

    @And("Admin Phone Number alanina TR kuralina uygun {int} haneli numara girer")
    public void adminPhoneNumberAlaninaTRKuralinaUygunHaneliNumaraGirer(int length) {
        WaitUtils.waitFor(2);
        int telDigits = length - 1;
        String phone = "5" + faker.number().digits(telDigits);
        TestData.phoneNumber = phone;
        homePage.registerPhoneNumberTextBox.sendKeys(phone);
    }

    @When("Admin register butonuna tiklar")
    public void adminButonunaTiklar() {
        WaitUtils.waitForClickablility(adminManagementPage.registerButton, 3);
        JSUtils.JSclickWithTimeout(adminManagementPage.registerButton);

    }


    @Then("Yeni Admin kaydının başarılı olduğu dogrulanir")
    public void adminKaydınınBaşarılıOlduğuDogrulanir() {

        TestData.createdAdminId = adminManagementPage.firstRowAdminId.getText();

        Assert.assertTrue(adminManagementPage.registerSuccesfulMessageText.isDisplayed());
        String actualTitle = adminManagementPage.registerSuccesfulMessageText.getText();
        Assert.assertTrue(
                actualTitle.contains("Registration successful! You can now log in.")
        );
        System.out.println("Yeni admin basariyla register olmustur.");

    }

    @And("Admin First Name alanini bos birakir")
    public void adminFirstNameAlaniniBosBirakir() {
        homePage.registerFirstNameTextBox.clear();
    }


    @Then("Yeni admin kaydının başarılı olmadığı doğrulanır")
    public void yeniAdminKaydınınBaşarılıOlmadığıDoğrulanır() {
        Assert.assertTrue(adminManagementPage.messageText.isDisplayed());
    }

    @And("Admin Last Name alanini bos birakir")
    public void adminLastNameAlaniniBosBirakir() {
        homePage.registerLastNameTextBox.clear();
    }

    @And("Admin Email alanini bos birakir")
    public void adminEmailAlaniniBosBirakir() {
        homePage.registerEmailTextBox.clear();
    }

    @And("Admin User Name alanini bos birakir")
    public void adminUserNameAlaniniBosBirakir() {
        homePage.registerUserNameTextBox.clear();
    }

    @And("Admin Password alanini bos birakir")
    public void adminPasswordAlaniniBosBirakir() {
        homePage.registerPasswordTextBox.clear();

    }

    @And("Admin Confirm Password alanini bos birakir")
    public void adminConfirmPasswordAlaniniBosBirakir() {
        adminManagementPage.confirmPasswordTextBox.clear();
    }

    @And("Admin Confirm Password alanina {string} yazar")
    public void adminConfirmPasswordAlaninaYazar(String confirmPasswordKey) {
        String confirmPasswordValue = (ConfigReader.getProperty(confirmPasswordKey) != null)
                ? ConfigReader.getProperty(confirmPasswordKey)
                : (confirmPasswordKey.equalsIgnoreCase("memoryPassword")
                ? TestData.password
                : confirmPasswordKey);

        homePage.registerConfirmPasswordTextBox.clear();
        homePage.registerConfirmPasswordTextBox.sendKeys(confirmPasswordValue);

    }


    @Then("{string} mesajinin görünürlüğü doğrulanır.")
    public void passwordMustBeAtLeastCharactersMesajininGörünürlüğüDoğrulanır() {
        Assert.assertTrue(adminManagementPage.passwordMustBeAtLeast4CharactersMessageText
                .isDisplayed());

    }

    @And("Admin Phone Number alanini bos birakir")
    public void adminPhoneNumberAlaniniBosBirakir() {
        adminManagementPage.phoneNumberTexBox.clear();
    }

    @And("Admin Address alanini bos birakir")
    public void adminAddressAlaniniBosBirakir() {
        homePage.registerAddressTextBox.clear();
    }

    @Then("Already Registered mesaji görüntülendiği doğrulanır.")
    public void alreadyRegisteredMesajiGörüntülendiğiDoğrulanır() {
        Assert.assertTrue(adminManagementPage.erorMessageText.isDisplayed());
    }

    @And("Admin Phone Number alanina {string} şeklinde numara girer")
    public void adminPhoneNumberAlaninaŞeklindeNumaraGirer(String phoneNumber) {
        if (phoneNumber.length()>10){
            String number = String.valueOf(faker.number().numberBetween(0,9));
            phoneNumber =  phoneNumber + number;
            adminManagementPage.phoneNumberTexBox.sendKeys(phoneNumber);

        }else {
            adminManagementPage.phoneNumberTexBox.sendKeys(phoneNumber);

        }
    }

    @Then("{string} uyari mesaji gorunur")
    public void invalidPhoneNumberUyariMesajiGorunur() {
        Assert.assertTrue(adminManagementPage.invalidPhoneNumberMessageText.isDisplayed());

    }

    @Then("Tum admin listesinin goruntulendigini kontrol edilir")
    public void tumAdminListesininGoruntulendiginiKontrolEdilir() {

        Assert.assertTrue(adminManagementPage.allAdminsTableTitle.isDisplayed());
        Assert.assertTrue(adminManagementPage.adminsTableHead.isDisplayed());
        Assert.assertTrue(adminManagementPage.adminsTableBody.isDisplayed());


    }

    @When("Admin Admin Management listesinden herhangi bir kaydin cop kutusu icon'ina tiklar")
    public void adminAdminManagementListesindenHerhangiBirKaydinCopKutusuIconInaTiklar() {

        TestData.expectedAdminId=adminManagementPage.firstRowAdminId.getText();
        ReusableMethods.waitForSecond(2);
        adminManagementPage.firstRowDeleteIcon.click();


    }

    @Then("Silinmesi hedeflenilen adminin listede olmadigi kontrol edilir")
    public void silinmesiHedeflenilenAdmininListedeOlmadigiKontrolEdilir() {

        ReusableMethods.waitForSecond(2);
        Assert.assertNotEquals(adminManagementPage.firstRowAdminId.getText(), TestData.expectedAdminId);
    }

    @When("Admin Management sayfasida admin@example.com satirinda ki cop kutusu icon'ina tiklar")
    public void adminManagementSayfasidaAdminExampleComSatirindaKiCopKutusuIconInaTiklar() {


        TestData.expectedAdminId= adminManagementPage.systemAdminId.getText();
        ReusableMethods.click(adminManagementPage.systemAdminDeleteIcon);

    }

    @Then("admin@example.com kaydinin listesinde kaldigi ve silinmedigi kontrol edilir")
    public void adminExampleComKaydininListesindeKaldigiVeSilinmedigiKontrolEdilir() {

        ReusableMethods.visibleWait(adminManagementPage.adminDeleteErrorAllert,3);
        Assert.assertTrue(adminManagementPage.adminDeleteErrorAllert.isDisplayed());
        Assert.assertEquals(adminManagementPage.systemAdminId.getText(), TestData.expectedAdminId);

    }

    @And("Admin ikinci sayfa butonuna tiklar")
    public void adminIkinciSayfaButonunaTiklar() {
        ReusableMethods.click(adminManagementPage.tableSecondPageButton);
        ReusableMethods.waitForSecond(2);
    }

}

