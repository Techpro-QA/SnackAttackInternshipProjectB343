package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.PaymentManagementPage;
import snackattack.utilities.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DB_PaymentManagementStepdefs {

    Map<String, Object> actualDatabase = new HashMap<>();
    SoftAssertions softAssert = new SoftAssertions();

    @When("Payments tablosu veritabaninda aranir")
    public void paymentsTablosuVeritabanindaAranir() throws SQLException {

        resultSet = statement.executeQuery("SELECT\n" +
                "    id,\n" +
                "    order_id,\n" +
                "    user_id,\n" +
                "    amount,\n" +
                "    currency,\n" +
                "    TO_CHAR(payment_date, 'DD.MM.YYYY HH24:MI:SS') AS payment_date_formatted\n" +
                "FROM snack_attack_db.payments\n" +
                "WHERE id = 57";

        ResultSet resultSet = DBUtils.executeQuery(query);

        if (resultSet.next()) {
            actualDatabase.put("id", resultSet.getInt("id"));
            actualDatabase.put("order_id", resultSet.getInt("order_id"));
            actualDatabase.put("user_id", resultSet.getInt("user_id"));
            actualDatabase.put("amount", resultSet.getDouble("amount"));
            actualDatabase.put("payment_date_formatted", resultSet.getString("payment_date_formatted"));
        }

        System.out.println(actualDatabase);
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

        softAssert.assertAll();

        // Test sonunda DB bağlantısını kapat
        DBUtils.closeConnection();

    }
}