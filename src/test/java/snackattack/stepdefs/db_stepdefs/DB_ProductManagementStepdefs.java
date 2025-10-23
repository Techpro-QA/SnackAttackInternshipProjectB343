package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.utilities.ConfigReader;

import java.sql.*;
import java.util.*;

public class DB_ProductManagementStepdefs {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    Map<Integer, Set<Integer>> actualMap;

    @Given("Veritabanı bağlantısı kurulur")
    public void veritabanıBağlantısıKurulur() throws SQLException {

        connection = DriverManager.getConnection(
                ConfigReader.getProperty("dbUrl"),
                ConfigReader.getProperty("dbUser"),
                ConfigReader.getProperty("dbPassword")
        );

        statement = connection.createStatement();

    }

    @When("Urun ekleme kategorileri tablosu veritabaninda aranir")
    public void urunEklemeKategorileriTablosuVeritabanindaAranir() throws SQLException {


        resultSet = statement.executeQuery("SELECT * FROM snack_attack_db.product_additions");

        actualMap = new HashMap<>();

        while (resultSet.next()) {
            int productId = resultSet.getInt("id");
            int categoryId = resultSet.getInt("addition_category_id");

            Set<Integer> categories = actualMap.containsKey(productId) ? actualMap.get(productId) : new HashSet<>();
            //Eğer productId map'te yoksa, yeni bir HashSet oluşturur ve ekler.
            categories.add(categoryId);
            actualMap.put(productId, categories);
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

            Assert.assertTrue("Ürün veritabanında bulundu: " + productId, actualMap.containsKey(productId));

            Set<Integer> actualCategories = actualMap.get(productId);
            Assert.assertEquals("Ürün ID: " + productId,
                    expectedCategories, actualCategories);
        }

    }

    @And("Baglanti kapatilir")
    public void baglantiKapatilir() {

        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Veritabanı bağlantısı kapatıldı.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}