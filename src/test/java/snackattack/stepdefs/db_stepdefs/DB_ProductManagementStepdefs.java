package snackattack.stepdefs.db_stepdefs;
import io.cucumber.java.en.*;
import org.junit.Assert;
import snackattack.utilities.DBUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class DB_ProductManagementStepdefs {
    Map<Integer, Set<Integer>> actualMap;

    @When("Urun ekleme kategorileri tablosu veritabaninda aranir")
    public void urunEklemeKategorileriTablosuVeritabanindaAranir() throws SQLException {
        ResultSet resultSet = DBUtils.executeQuery("SELECT * FROM snack_attack_db.product_additions");
        actualMap = new HashMap<>();
        while (resultSet.next()) {
            int productId = resultSet.getInt("id");
            int categoryId = resultSet.getInt("addition_category_id");
            // Eğer productId yoksa yeni HashSet ekle, varsa mevcut sete kategori ekle
            actualMap.computeIfAbsent(productId, k -> new HashSet<>()).add(categoryId);
        }
    }
    @Then("Kategori bilgileri veritabaninda bulunmalidir")
    public void kategoriBilgileriVeritabanindaBulunmalidir() {
        Map<Integer, Set<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(1, new HashSet<>(Arrays.asList(1)));
        expectedMap.put(2, new HashSet<>(Collections.singletonList(1)));
        expectedMap.put(3, new HashSet<>(Collections.singletonList(1)));
        for (Map.Entry<Integer, Set<Integer>> entry : expectedMap.entrySet()) {
            int productId = entry.getKey();
            Set<Integer> expectedCategories = entry.getValue();
            Assert.assertTrue(":x: Ürün veritabanında bulunamadı: " + productId,
                    actualMap.containsKey(productId));
            Set<Integer> actualCategories = actualMap.get(productId);
            Assert.assertEquals(":x: Kategori eşleşmesi hatalı. Ürün ID: " + productId,
                    expectedCategories, actualCategories);
        }
        System.out.println(":white_check_mark: Tüm kategori eşleşmeleri başarıyla doğrulandı.");
    }

}