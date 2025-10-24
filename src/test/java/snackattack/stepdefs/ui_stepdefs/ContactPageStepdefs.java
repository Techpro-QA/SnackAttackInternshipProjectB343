package snackattack.stepdefs.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import snackattack.pages.ContactPage;
import snackattack.pages.HomePage;
import snackattack.utilities.ActionsUtils;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.ReusableMethods;

public class ContactPageStepdefs {

    HomePage homePage = new HomePage();
    ContactPage contactPage = new ContactPage();
    Faker faker = new Faker();


    @And("Acilan pencerede Name icin gecerli bir data girilir")
    public void acilanPenceredeNameIcinGecerliBirDataGirilir() {

        contactPage.nameTextBox.sendKeys("Sahin");
    }

    @And("Email  icin gecerli bir data girilir")
    public void emailIcinGecerliBirDataGirilir() {

        contactPage.emailTextBox.sendKeys(ConfigReader.getProperty("userEmail"));
    }

    @And("Subject alani doldurulur")
    public void subjectAlaniDoldurulur() {

        contactPage.subjectTextBox.sendKeys("Uyarı");
    }

    @And("Message alani en az {int} karakter ile doldurulur.")
    public void messageAlaniEnAzKarakterIleDoldurulur(int arg0) {

        contactPage.messageTextBox.sendKeys(faker.lorem().sentence(12));
    }


    @And("Kullanıcı consent  kutucugunu  isaretler")
    public void kullanıcıConsentKutucugunuIsaretler() {

        ActionsUtils.scrollDown();
        WebElement checkbox = contactPage.consentClickBox;
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

    }

    @And("Send butonu tıklanir")
    public void sendButonuTıklanir() {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", contactPage.SubmitContactButton);

    }

    @Then("Kayit isleminin basarili bir sekilde gerceklestigi dogrulanir\"")
    public void kayitIslemininBasariliBirSekildeGerceklestigiDogrulanir() throws Throwable {

        Assert.assertTrue(contactPage.ContactMessageVerification.isDisplayed());
    }

    @And("Acilan pencerede Name alani bos birakilir")
    public void acilanPenceredeNameAlaniBosBirakilir() {

        contactPage.nameTextBox.sendKeys("");
    }

  //
    // @Then("{string} goruntulenmeli")@Then("Lutfen bu alani doldurun mesaji goruntulenmeli")
  //
    //     public void validation_mesaji_goruntulenmeli(String expectedMessage) {public void lutfenBuAlaniDoldurunMesajiGoruntulenmeli() {


  //
    //                 WebElement firstInvalid = driver.switchTo().activeElement();JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
  //
    //                 JavascriptExecutor js = (JavascriptExecutor) driver;String actualMessage = (String) js.executeScript("return arguments[0].validationMessage;", contactPage.nameTextBox);
  //
    //                 String actualMessage = (String) js.executeScript(System.out.println("Validation mesajı: " + actualMessage);
  //
    //                         "return arguments[0].validationMessage;", firstInvalidAssert.assertEquals("Validation mesajı eşleşmiyor!", "Lütfen bu alanı doldurun.", actualMessage);

  //
    //         );ReusableMethods.checkValidationMessage(Driver.getDriver(),contactPage.nameTextBox,"Lütfen bu alanı doldurun.");
  // }

    @And("Email  alani bos birakilir.")
    public void emailAlaniBosBirakilir() {

        contactPage.emailTextBox.sendKeys("");
    }

    @And("Email  alaninda @ isareti yazilmaz")
    public void emailAlanindaIsaretiYazilmaz() {

        contactPage.emailTextBox.sendKeys("sahinbatch.com");
    }


    @And("Subject alanini bos birakir")
    public void subjectAlaniniBosBirakir() {

        contactPage.subjectTextBox.sendKeys("");
    }



    @And("Message alanini bos birakir")
    public void messageAlaniniBosBirakir() {

        contactPage.messageTextBox.sendKeys("");
    }

    @And("Kullanıcı consent  kutucugunu  isaretlenmez")
    public void kullanıcıConsentKutucugunuIsaretlenmez() {

    }

    @Then("{string} goruntulenmeli")
        public void validation_mesaji_goruntulenmeli(String expectedMessage) {
            WebElement firstInvalid = Driver.getDriver().switchTo().activeElement();
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            String actualMessage = (String) js.executeScript(
                    "return arguments[0].validationMessage;", firstInvalid
            );
            System.out.println("Validation mesajı: " + actualMessage);
            Assert.assertEquals(expectedMessage, actualMessage);
        }
    }


