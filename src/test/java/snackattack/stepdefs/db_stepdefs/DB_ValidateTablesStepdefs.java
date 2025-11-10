package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.*;
import snackattack.utilities.DBUtils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_ValidateTablesStepdefs {

    private List<String> actualTables = new ArrayList<>();

    @Given("veritabanına bağlantı kurabilirim")
    public void veritabaniBaglantisiKurabilirim() {
        DBUtils.createConnection();
        if (DBUtils.getConnection() == null) {
            throw new RuntimeException("Veritabanı bağlantısı kurulamadı!");
        }
    }

    @When("beklenen tabloları sorgularım")
    public void beklenenTablolariSorgularim() throws SQLException {
        DatabaseMetaData metaData = DBUtils.getConnection().getMetaData();
        ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            actualTables.add(rs.getString("TABLE_NAME").toLowerCase());
        }
        rs.close();
    }

    @Then("tüm tablolar mevcut olmalı")
    public void tumTablolarMevcutOlmali(List<String> expectedTables) {
        for (String table : expectedTables) {
            if (!actualTables.contains(table.toLowerCase())) {
                throw new RuntimeException("Eksik tablo bulundu: " + table);
            }
        }
    }

    @And("eksik tablo varsa hata mesajı dönmeli")
    public void eksikTabloVarsaHataMesajiDonmeli(List<String> expectedTables) {
        List<String> missing = new ArrayList<>();
        for (String table : expectedTables) {
            if (!actualTables.contains(table.toLowerCase())) {
                missing.add(table);
            }
        }
        if (!missing.isEmpty()) {
            throw new RuntimeException("Eksik tablolar: " + String.join(", ", missing));
        }
    }

    @And("veritabanı bağlantısı kapatılır")
    public void veritabaniBaglantisiKapatilir() {
        DBUtils.closeConnection();
    }
}
