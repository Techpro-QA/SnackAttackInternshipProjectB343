package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.utilities.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DB_ContactMessageStepdefs {

    /*Connection connection;
    Statement statement;
    ResultSet resultSet;
    Map<String, String> actualData;

    @When("{string} email adresine ait kayit sorgulanir")
    public void emailAdresineAitKayitSorgulanir(String email) throws SQLException {
        String query = "SELECT * FROM contact_message WHERE email = '" + email + "'";
        resultSet = statement.executeQuery(query);
        actualData = new HashMap<>();

        if (resultSet.next()) {
            actualData.put("email", resultSet.getString("email"));
            actualData.put("name", resultSet.getString("name"));
            actualData.put("message", resultSet.getString("message"));
            actualData.put("subject", resultSet.getString("subject"));
        }
    }

    @Then("Email {string} icin name {string} message {string} subject {string} olmalidir")
    public void emailIcinNameMessageSubjectOlmalidir(String email, String expectedName, String expectedMessage, String expectedSubject) {
        Assert.assertEquals(email, actualData.get("email"));
        Assert.assertEquals(expectedName, actualData.get("name"));
        Assert.assertEquals(expectedMessage, actualData.get("message"));
        Assert.assertEquals(expectedSubject, actualData.get("subject"));
    }

    @Then("Database baglantisi kapatilir")
    public void databaseBaglantisiKapatilir() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
*/

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    Map<String, String> actualData;

    @When("{string} email adresine ait kayit sorgulanir")
    public void emailAdresineAitKayitSorgulanir(String email) throws SQLException {
        String query = "SELECT * FROM snack_attack_db.contact_message WHERE email = '" + email + "'";
        resultSet = DBUtils.executeQuery(query);
        actualData = new HashMap<>();

        if (resultSet.next()) {
            actualData.put("email", resultSet.getString("email").trim());
            actualData.put("name", resultSet.getString("name").trim());
            actualData.put("message", resultSet.getString("message").trim());
            actualData.put("subject", resultSet.getString("subject").trim());
        } else {
            Assert.fail("Veritabanında bu email adresine ait kayıt bulunamadı: " + email);
        }
    }

    @Then("Email {string} icin name {string} message {string} subject {string} olmalidir")
    public void emailIcinNameMessageSubjectOlmalidir(String email, String expectedName, String expectedMessage, String expectedSubject) {
        Assert.assertEquals("Email uyuşmuyor!", email, actualData.get("email"));
        Assert.assertEquals("Name uyuşmuyor!", expectedName, actualData.get("name"));
        Assert.assertEquals("Message uyuşmuyor!", expectedMessage, actualData.get("message"));
        Assert.assertEquals("Subject uyuşmuyor!", expectedSubject, actualData.get("subject"));
    }

    @Then("Database baglantisi kapatilir")
    public void databaseBaglantisiKapatilir() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }


}
