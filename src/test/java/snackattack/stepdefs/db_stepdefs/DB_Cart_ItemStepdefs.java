package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.*;
import org.junit.Assert;
import snackattack.utilities.DBUtils; // DBUtils sınıfınızın importu
import java.sql.*;
import java.util.*;
import io.cucumber.java.en.Then;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DB_Cart_ItemStepdefs {
    List<String> columnNames;
    List<Map<String, Object>> allRowsAsMap; // Tüm veriyi Map listesi olarak tutar
    ResultSet resultSet;
    ResultSetMetaData metaData;

    @When("Kullanici {string} sorgusunu calistirir")
    public void kullaniciSorgusunuCalistirir(String query) {

        try {
              resultSet = DBUtils.executeQuery(query);

            if (resultSet != null) {
                metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // 1. Sütun Adlarını Çekme (columnNames'i doldurur)
                columnNames = new ArrayList<>();
                for(int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                // 2. Verileri Map listesine Çekme (allRowsAsMap'i doldurur)
                allRowsAsMap = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    for(int i = 1; i <= columnCount; i++) {
                        // Veriyi sütun adına göre Map'e ekle
                        row.put(columnNames.get(i-1), resultSet.getObject(i));
                    }
                    allRowsAsMap.add(row);
                }
                resultSet.close(); // ResultSet'i kapattık

            } else {
                Assert.fail("Sorgu sonucu bos geldi (ResultSet null). Sorgu: " + query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Veritabanı sorgusu çalıştırılırken veya sonuçları işlenirken SQL hatası oluştu: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Sorgu sonucu işlenirken beklenmeyen bir hata oluştu: " + e.getMessage());
        }
    }

    @Then("Gelen cevapta asagidaki sutun isimleri dogrulanir:")
    public void gelenCevaptaAsagidakiSutunIsimleriDogrulanir(List<String> expectedColumns) {
        System.out.println("Beklenen Sutunlar: " + expectedColumns);
        System.out.println("Gerceklesen Sutunlar: " + columnNames);

        if (columnNames == null) {
            Assert.fail("Sorgu sonucu sütunları bulunamadı (columnNames null).");
        }

        assertEquals("Sutun sayisi eslesmiyor.", expectedColumns.size(), columnNames.size());

        for (String expectedColumn : expectedColumns) {
            assertTrue("Sutun adi eslesmiyor: " + expectedColumn, columnNames.contains(expectedColumn));
        }
        System.out.println("Sutun isimleri dogrulandi.");
    }

    @And("Gelen cevapta asagidaki verilerin bulundugu dogrulanir:")
    public void gelenCevaptaAsagidakiVerilerinBulunduguDogrulanir(List<Map<String, String>> expectedRows) {

        if (allRowsAsMap == null) {
            Assert.fail("@When adımı çalıştırılmadı veya veri çekilemedi. allRowsAsMap null.");
        }

        for (Map<String, String> expectedRow : expectedRows) {
            boolean found = false;

            // Veritabanından gelen tüm kayıtlar arasında döngü yap
            for (Map<String, Object> actualRow : allRowsAsMap) {
                boolean match = true;

                // Beklenen satırdaki her sütun-değer çiftini kontrol et
                for (Map.Entry<String, String> entry : expectedRow.entrySet()) {
                    String expectedValue = entry.getValue();
                    String key = entry.getKey();

                    if (!actualRow.containsKey(key) || actualRow.get(key) == null) {
                        match = false;
                        break;
                    }

                    // Değerleri karşılaştırırken esnek ol (özellikle tarih/saat için 'contains' kullanıldı)
                    String actualValueString = actualRow.get(key).toString().trim();

                    // contains metodu, tarih gibi kırılgan verilerde esneklik sağlar.
                    if (!actualValueString.contains(expectedValue)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    found = true;
                    break;
                }
            }

            assertTrue("HATA: Beklenen satir veritabanı sonucunda bulunamadı: " + expectedRow, found);
        }
        System.out.println("Tüm beklenen kritik satır verileri başarıyla doğrulandı.");
    }

}