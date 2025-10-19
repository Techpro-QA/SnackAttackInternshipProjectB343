package snackattack.stepdefs.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import snackattack.pages.ContactPage;
import snackattack.pages.HomePage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;

public class ContactPageStepdefs {

    HomePage homePage = new HomePage();
    ContactPage contactPage = new ContactPage();
    Faker faker = new Faker();


    @And("Anasayfadaki Contact butonuna tiklanir.")
    public void anasayfadakiContactButonunaTiklanir() {

       homePage.homeContact.click();

    }

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


    @And("{string} kutucugu isaretlenir.")
    public void kutucuguIsaretlenir(String arg0) {

        contactPage.consentClickBox.click();
    }


    @And("Send butonu tıklanir")
    public void sendButonuTıklanir() {

        contactPage.SubmitContactButton.click();
    }

    @Then("Kayit isleminin basarili bir sekilde gerceklestigi dogrulanir\"")
    public void kayitIslemininBasariliBirSekildeGerceklestigiDogrulanir() throws Throwable {

        Assert.assertTrue(contactPage.ContactMessageVerification.isDisplayed());
    }

}

