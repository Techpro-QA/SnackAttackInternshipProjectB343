package snackattack.stepdefs.ui_stepdefs;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import snackattack.pages.ContactPage;
import snackattack.utilities.*;


public class ContactPageStepdefs {

    ContactPage contactPage = new ContactPage();
    Faker faker = new Faker();



    @And("Acilan pencerede Name icin gecerli bir data girilir")
    public void acilanPenceredeNameIcinGecerliBirDataGirilir() {
        String firstName = faker.name().firstName();
        TestData.firstName = firstName;
        contactPage.nameTextBox.sendKeys(firstName);
    }

    @And("Email icin gecerli bir data girilir")
    public void emailIcinGecerliBirDataGirilir() {
        String email = faker.internet().emailAddress();
        TestData.email = email;
        contactPage.emailTextBox.sendKeys(email);
    }

    @And("Subject alani {string} doldurulur")
    public void subjectAlaniDoldurulur(String subject) {
        TestData.subject = subject.equalsIgnoreCase("random")
                ? faker.lorem().sentence(3)
                : subject;
        contactPage.subjectTextBox.sendKeys(TestData.subject);
    }


    @And("Message alani en az {int} karakter ile {string} doldurulur.")
    public void messageAlaniEnAzKarakterIleDoldurulur(int number, String message) {

        TestData.message = message.equalsIgnoreCase("random") ? faker.lorem().sentence(8) : message;
        contactPage.messageTextBox.sendKeys(TestData.message);

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

    @Then("Kayit isleminin basarili bir sekilde gerceklestigi dogrulanir")
    public void kayitIslemininBasariliBirSekildeGerceklestigiDogrulanir() throws Throwable {

        Assert.assertTrue(contactPage.ContactMessageVerification.isDisplayed());
    }

    @And("Acilan pencerede Name alani bos birakilir")
    public void acilanPenceredeNameAlaniBosBirakilir() {

        contactPage.nameTextBox.sendKeys("");
    }


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


