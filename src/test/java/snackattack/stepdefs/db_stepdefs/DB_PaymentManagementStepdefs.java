package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.PaymentManagementPage;
import snackattack.utilities.DBUtils;
import snackattack.utilities.TestData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DB_PaymentManagementStepdefs {

    public static Map<String, Object> actualDatabase = new HashMap<>();
    SoftAssertions softAssert = new SoftAssertions();

    @When("Payments tablosu veritabaninda aranir")
    public void paymentsTablosuVeritabanindaAranir() throws SQLException {

        // SQL sorgusuna username ekledik
        String query = "SELECT\n" +
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

        // Sabit id ve username doğrulaması
        softAssert.assertThat(actualDatabase.get("id")).isEqualTo(57);
        softAssert.assertThat(actualDatabase.get("order_id")).isEqualTo(65);
        softAssert.assertThat(actualDatabase.get("user_id")).isEqualTo(32);
        softAssert.assertThat((Double) actualDatabase.get("amount")).isEqualTo(570.0);
        softAssert.assertThat(actualDatabase.get("payment_date_formatted")).isEqualTo("24.10.2025 13:08:42");

        softAssert.assertAll();

        // Test sonunda DB bağlantısını kapat
        DBUtils.closeConnection();

    }



    @When("Payments tablosundaki son ödeme bilgileri veritabaninda aranir")
    public void paymentsTablosundakiSonÖdemeBilgileriVeritabanindaAranir() throws SQLException {

        String query = "SELECT\n" +
                "    id,\n" +
                "    order_id,\n" +
                "    user_id,\n" +
                "    amount,\n" +
                "    currency,\n" +
                "    TO_CHAR(payment_date, 'DD.MM.YYYY HH24:MI:SS') AS payment_date_formatted\n" +
                "FROM snack_attack_db.payments\n" +
                "ORDER BY payment_date desc \n" +
                "LIMIT 1;";

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

    @Then("DB’deki ödeme bilgileri UI’den dönen bilgilerle tutarlı olmalı")
    public void dbDekiÖdemeBilgileriAPIDenDönenBilgilerleTutarlıOlmalı() {
// id ve order_id string olarak karşılaştır
        assertEquals(TestData.expectedFirstRowPaymentId, String.valueOf(actualDatabase.get("id")));
        assertEquals(TestData.expectedFirstRowOrderId, String.valueOf(actualDatabase.get("order_id")));

// amount'u "$" ve 2 ondalık basamak ile karşılaştır
        DecimalFormat df = new DecimalFormat("0.00");
        String actualAmountFormatted = "$" + df.format(actualDatabase.get("amount"));
        assertEquals(TestData.expectedFirstRowAmount, actualAmountFormatted);

// payment_date zaten string, direkt karşılaştır
        assertEquals(TestData.expectedFirstRowPaymentDate, actualDatabase.get("payment_date_formatted"));

    }
}