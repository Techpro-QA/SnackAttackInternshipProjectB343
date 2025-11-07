package snackattack.stepdefs.e2e_stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import snackattack.pages.ContactPage;
import snackattack.utilities.DBUtils;
import snackattack.utilities.Driver;
import snackattack.utilities.TestData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static snackattack.utilities.TestData.email;


public class ContactMessageE2EStepdefs {

    ContactPage contactPage = new ContactPage();
    Response response;
    ResultSet resultSet;

    // UI KISMI
    @When("Kullanıcı formu aşağıdaki bilgilerle doldurur ve gönderir")
    public void kullanıcıFormuAşağıdakiBilgilerleDoldururVeGönderir(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = dataList.get(0);
        contactPage.nameTextBox.sendKeys(data.get("name"));
        contactPage.emailTextBox.sendKeys(data.get("email"));
        contactPage.subjectTextBox.sendKeys(data.get("subject"));
        contactPage.messageTextBox.sendKeys(data.get("message"));
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", contactPage.consentClickBox);
        js.executeScript("arguments[0].click();", contactPage.SubmitContactButton);

    }


    @Then("Then Kullanıcı mesaj oluşturuldu bilgisini görür")
    public void thenKullanıcıMesajOluşturulduBilgisiniGörür() {

        String actual = contactPage.ContactMessageVerification.getText();

        Pattern pattern = Pattern.compile("id (\\d+)");
        Matcher matcher = pattern.matcher(actual);
        if (matcher.find()) {
            String messageId = matcher.group(1);
            TestData.createdMessageId = messageId;
            String expected = "Contact Message with id " + messageId + " is CREATED Successfully";
            Assert.assertEquals(expected, actual);
        } else {
            Assert.fail("Mesajda ID bulunamadı!");
        }
    }

    // API KISMI
    @And("API üzerinden aynı email için gönderilen mesajlar çekilir")
    public void apiÜzerindenAynıEmailIçinGönderilenMesajlarÇekilir() {

        String email = TestData.email;
        response = RestAssured
                .given()
                .baseUri("http://207.154.209.12:8080")
                .when()
                .get("/contactMessages/search?email=" + email);

        response.prettyPrint();

    }

    @Then("API Response status code {int} olmalıdır")
    public void apiResponseStatusCodeOlmalıdır(int kod) {
        Assert.assertEquals(kod, response.getStatusCode());

    }

    @And("API response içinde gönderilen mesaj bilgileri doğru olmalıdır")
    public void apiResponseIçindeGönderilenMesajBilgileriDoğruOlmalıdır(DataTable dataTable) {

        Map<String, String> expectedData = dataTable.asMaps().get(0);

        List<Map<String, Object>> messages = response.jsonPath().getList("content");

        Map<String, Object> lastMessage = messages.get(messages.size() - 1);

        Assert.assertEquals(expectedData.get("name"), lastMessage.get("name"));
        Assert.assertEquals(expectedData.get("email"), lastMessage.get("email"));
        Assert.assertEquals(expectedData.get("subject"), lastMessage.get("subject"));
        Assert.assertEquals(expectedData.get("message"), lastMessage.get("message"));
    }
    // DB KISMI

    @When("{string} email adresine ait kayit sorgulanir")
    public void emailAdresineAitKayitSorgulanir(String email) {

        String query = "SELECT * FROM snack_attack_db.contact_message WHERE email = '" + email + "' ORDER BY id DESC LIMIT 1";
        resultSet = DBUtils.executeQuery(query);

    }

    @Then("Email {string} icin name {string} message {string} subject {string} olmalidır")
    public void emailIcinNameMessageSubjectOlmalidir(String email, String expectedName, String expectedMessage, String expectedSubject) throws SQLException {
        if (resultSet.next()) {
            String actualEmail = resultSet.getString("email");
            String actualName = resultSet.getString("name");
            String actualMessage = resultSet.getString("message");
            String actualSubject = resultSet.getString("subject");

            System.out.println("🧾 Veritabanı Kaydı:");
            System.out.println("Email   : " + actualEmail);
            System.out.println("Name    : " + actualName);
            System.out.println("Message : " + actualMessage);
            System.out.println("Subject : " + actualSubject);

            Assert.assertEquals("Email uyuşmuyor!", email, actualEmail);
            Assert.assertEquals("Name uyuşmuyor!", expectedName, actualName);
            Assert.assertEquals("Message uyuşmuyor!", expectedMessage, actualMessage);
            Assert.assertEquals("Subject uyuşmuyor!", expectedSubject, actualSubject);
        } else {
            Assert.fail(" Verilen email adresine ait kayıt bulunamadı: " + email);
        }
    }
}
