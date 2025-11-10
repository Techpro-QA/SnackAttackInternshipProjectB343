package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.*;
import org.junit.After;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_CategoryEntityStepDefs {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Given("Database'e baglan")
    public void database_e_baglan() throws SQLException {
        String dbUrl = "jdbc:postgresql://64.227.123.49:5432/snack_attack_db";
        String dbUser = "postgres";
        String dbPassword = "changeme";

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        System.out.println("✅ Veritabanı bağlantısı kuruldu.");
    }

    @Then("{string} sutunu boş olmamalıdır")
    public void sutunu_bos_olmamalidir(String columnName) throws SQLException {

        // 🔹 ResultSet’i başa al
        resultSet.beforeFirst();
        List<Integer> bosIdler = new ArrayList<>();

        // 🔹 Satırları kontrol et
        while (resultSet.next()) {
            String value = resultSet.getString(columnName);

            // Eğer sütun integer tipindeyse, getString null dönebilir.
            // Bu nedenle getObject ile de kontrol yapıyoruz:
            if (value == null && resultSet.getObject(columnName) == null) {
                bosIdler.add(resultSet.getInt("id"));
            } else if (value != null && value.trim().isEmpty()) {
                bosIdler.add(resultSet.getInt("id"));
            }
        }

        // 🔹 Sonuç yazdırma
        if (bosIdler.isEmpty()) {
            System.out.println("✅ \"" + columnName + "\" sutununda boş kayıt bulunamadı.");
        } else {
            System.out.println("⚠️ \"" + columnName + "\" sutunu boş olan ID'ler: " + bosIdler);
        }

        // 🔹 Doğrulama
        Assert.assertTrue("Bazı \"" + columnName + "\" sutunları boş! ID'ler: " + bosIdler, bosIdler.isEmpty());
    }

    // 🔹 Tüm adımlar bitince otomatik kapanması için (opsiyonel)
    @After
    public void kapat() throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) resultSet.close();
        if (statement != null && !statement.isClosed()) statement.close();
        if (connection != null && !connection.isClosed()) connection.close();
        System.out.println("🔒 Database bağlantısı kapatıldı.");
    }

    @When("{string} tablosundaki tüm kayıtları al")
    public void tablosundakiTümKayıtlarıAl(String tablename) throws SQLException {
        String query = "SELECT * FROM snack_attack_db." + tablename;
        resultSet = statement.executeQuery(query);
        System.out.println("📦 Sorgu çalıştı: " + query);
    }
}
