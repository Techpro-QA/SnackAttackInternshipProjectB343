package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.PaymentManagementPage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.JSUtils;
import snackattack.utilities.TestData;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class DB_PaymentManagementStepdefs {
    HomePage homePage = new HomePage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    PaymentManagementPage paymentManagementPage = new PaymentManagementPage();
    Map<String, Object> actualDatabaseFirstRow;
    SoftAssertions softAssert;

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("Database baglantisi kurulur")
    public void databaseBaglantisiKurulur() throws SQLException {
        connection = DriverManager.getConnection(
                ConfigReader.getProperty("dbUrl"),
                ConfigReader.getProperty("dbUser"),
                ConfigReader.getProperty("dbPassword")
        );

        statement = connection.createStatement();
    }

    @When("Payments tablosu veritabaninda aranir")
    public void paymentsTablosuVeritabanindaAranir() throws SQLException {

        resultSet = statement.executeQuery("SELECT\n" +
                "    id,\n" +
                "    order_id,\n" +
                "    user_id,\n" +
                "    amount,\n" +
                "    TO_CHAR(payment_date, 'DD.MM.YYYY HH24:MI:SS') AS payment_date_formatted\n" +
                "FROM snack_attack_db.payments\n" +
                "ORDER BY payment_date DESC\n" +
                "LIMIT 1;");


        actualDatabaseFirstRow = new HashMap<>();

        if (resultSet.next()) {
            actualDatabaseFirstRow.put("id", resultSet.getInt("id"));
            actualDatabaseFirstRow.put("order_id", resultSet.getInt("order_id"));
            actualDatabaseFirstRow.put("user_id", resultSet.getInt("user_id"));
            actualDatabaseFirstRow.put("amount", resultSet.getDouble("amount"));
            actualDatabaseFirstRow.put("payment_date_formatted", resultSet.getString("payment_date_formatted"));
        }

    }


      @And("Payment bilgileri UI dan alinir")
      public void paymentBilgileriUIDanAlinir() {
          Driver.getDriver().get(ConfigReader.getProperty("snackUrl"));
          homePage.homeLoginRegisterButton.click();
          homePage.loginEmailTextBox.sendKeys(ConfigReader.getProperty("adminEmail"));
          homePage.loginPasswordTextBox.sendKeys(ConfigReader.getProperty("adminPassword"));
          homePage.loginButton.click();
          JSUtils.JSscrollIntoView(adminPanelPage.paymentManagementMenu);
          adminPanelPage.paymentManagementMenu.click();

          String firstRowPaymentIdText = paymentManagementPage.firstRowPaymentId.getText();
          TestData.expectedFirstRowPaymentId=firstRowPaymentIdText;

          String firstRowOrderIdText = paymentManagementPage.firstRowOrderId.getText();
          TestData.expectedFirstRowOrderId=firstRowOrderIdText;

          String firstRowUserIdText = paymentManagementPage.firstRowUserId.getText();
          TestData.expectedFirstRowUserId=firstRowUserIdText;

          String firstRowAmountText = paymentManagementPage.firstRowAmount.getText();
          TestData.expectedFirstRowAmount=firstRowAmountText;

          String firstRowPaymentDateText = paymentManagementPage.firstRowPaymentDate.getText();
          TestData.expectedFirstRowPaymentDate=firstRowPaymentDateText;
          Driver.closeDriver();
      }

    @Then("Payment bilgileri dogrulanir")
    public void paymentBilgileriDogrulanir() {

        softAssert = new SoftAssertions();

        // Payment ID kontrolü
        softAssert.assertThat(TestData.expectedFirstRowPaymentId)
                .as("Payment ID kontrolü")
                .isEqualTo(actualDatabaseFirstRow.get("id").toString());

        // Order ID kontrolü
        softAssert.assertThat(TestData.expectedFirstRowOrderId)
                .as("Order ID kontrolü")
                .isEqualTo(actualDatabaseFirstRow.get("order_id").toString());

        // User ID kontrolü
        softAssert.assertThat(TestData.expectedFirstRowUserId)
                .as("User ID kontrolü")
                .isEqualTo(actualDatabaseFirstRow.get("user_id").toString());


        //$ işaretini kaldirdim ve ondalık formatını esitledim
        String uiAmount = TestData.expectedFirstRowAmount.replace("$", "").trim();

        //Database’den gelen değeri alıp UI’daki gibi iki ondalıklı formatta String’e cevirdim
        String dbAmount = String.format("%.2f", Double.parseDouble(actualDatabaseFirstRow.get("amount").toString()));

        // Amount kontrolü
        softAssert.assertThat(uiAmount)
                .as("Amount kontrolü")
                .isEqualTo(dbAmount);

        // Payment Date kontrolü
        softAssert.assertThat(TestData.expectedFirstRowPaymentDate)
                .as("Payment Date kontrolü")
                .isEqualTo(actualDatabaseFirstRow.get("payment_date_formatted").toString());

        // Tüm soft assertion sonuclarini raporla
        softAssert.assertAll();
    }

}
