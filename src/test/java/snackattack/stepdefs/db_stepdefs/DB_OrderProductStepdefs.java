package snackattack.stepdefs.db_stepdefs;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;

import org.assertj.core.api.SoftAssertions;
import snackattack.utilities.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DB_OrderProductStepdefs {

    private final Map<String, Object> actualDatabase = new HashMap<>();
    private final SoftAssertions softAssert = new SoftAssertions();
    private Connection conn;
    private final Map<String, Object> us11Actual = new HashMap<>();
    ;

    @Given("Veritabanıyla bağlantı kurulur")
    public void veritabaniBaglantisiKurulur() {
        DBUtils.createConnection();
        conn = DBUtils.getConnection();        if (conn == null) {
            throw new IllegalStateException("DBUtils.getConnection() null döndü! " +
                    "DBUtils içinde connection set edilmiyor olabilir.");
        }
        System.out.println("✅ Veritabanı bağlantısı kuruldu.");
    }



    @When("Siparise ait urunlerin tablosu veritabaninda aranir")
    public void sipariseAitUrunlerinTablosuVeritabanindaAranir() throws Exception {

        int targetOrderId = 65;

        String sql = "SELECT order_id, product_name, price, quantity " +
                "FROM snack_attack_db.order_items " +
                "WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, targetOrderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    actualDatabase.put("order_id", rs.getInt("order_id"));
                    actualDatabase.put("product_name", rs.getString("product_name"));
                    actualDatabase.put("price", rs.getDouble("price"));
                    actualDatabase.put("quantity", rs.getInt("quantity"));
                } else {
                    throw new AssertionError("Veritabanında order_id=" + targetOrderId + " için kayıt bulunamadı!");
                }
            }
        }

        System.out.println("Veritabanından gelen ürün bilgileri: " + actualDatabase);
    }



    @Then("Siparise ait urunlerin kategori bilgileri veritabaninda bulunmalidir")
    public void siparislereAitUrunKategoriBilgileriVeritabanindaBulunmalidir() {

        softAssert.assertThat(actualDatabase.get("order_id")).as("order_id").isEqualTo(65);
        softAssert.assertThat(actualDatabase.get("product_name")).as("product_name").isEqualTo("Baklava (Fıstıklı)");
        softAssert.assertThat((Double) actualDatabase.get("price")).as("price").isEqualTo(40.0);
        softAssert.assertThat(actualDatabase.get("quantity")).as("quantity").isEqualTo(8);

        softAssert.assertAll();
        System.out.println("Kategori ve ürün bilgileri başarıyla doğrulandı.");
    }



    @When("Siparis tablosu veritabaninda aranir")
        public void siparisTablosuVeritabanindaAranir() throws Exception {

            int targetId = 10;

            // orders tablosundan sipariş bilgilerini al
            String sql = "SELECT id, order_date,total_price , order_time " +
                    "FROM snack_attack_db.orders WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, targetId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        us11Actual.put("order_date", rs.getString("order_date"));
                        us11Actual.put("total_price", rs.getInt("total_price"));
                        us11Actual.put("order_time", rs.getString("order_time"));

                    } else {
                        throw new AssertionError("orders tablosunda id=" +
                                targetId + " için kayıt bulunamadı!");
                    }
                }
            }

            System.out.println("US_11 >> orders tablosundan gelen kayıt: " + us11Actual);
    }

    @Then("Siparis Kategori bilgileri veritabaninda bulunmalidir")
    public void siparisKategoriBilgileriVeritabanindaBulunmalidir() {
        // orders tablosundan çekilen değerleri doğruluyoruz

        softAssert.assertThat((String) us11Actual.get("order_date"))
                .as("order_date")
                .isNotNull()
                .isNotBlank();

        softAssert.assertThat(us11Actual.get("total_price"))
                .as("total_price")
                .isNotNull();


        softAssert.assertAll();
        System.out.println("✅ US_11 >> Sipariş temel bilgileri başarıyla doğrulandı.");
    }



}




