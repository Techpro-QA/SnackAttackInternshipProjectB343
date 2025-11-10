package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.utilities.DBUtils;
import snackattack.utilities.TestData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_ProductAdditionCategoryStepdefs {

    ResultSet resultSet;

    @Then("product_addition_category isimleri ve bilgileri doğrulanır")
    public void productAdditionCategoryIsimleriVeBilgileriDoğrulanır() throws SQLException {

        resultSet.next();
        Assert.assertEquals(33, resultSet.getInt("product_id"));
        resultSet.next();
        Assert.assertEquals(55, resultSet.getInt("product_id"));
        resultSet.next();
        Assert.assertEquals(81, resultSet.getInt("product_id"));

    }


    @Then("product_additions tablosundaki isimler doğrulanır")
    public void product_additionsTablosundakiIsimlerDoğrulanır() throws SQLException {

        resultSet.next();
        Assert.assertEquals("Acili ezme", resultSet.getString("name"));
        resultSet.next();
        Assert.assertEquals("Yogun Baharatli ezme", resultSet.getString("name"));

    }


    @Then("product_additions tablosundaki bütün datalar doğrulanır")
    public void product_additionsTablosundakiBütünDatalarDoğrulanır() throws SQLException {

        resultSet.next();
        Assert.assertTrue(resultSet.getBoolean("active"));
        Assert.assertEquals(5, resultSet.getInt("price"));
        Assert.assertEquals(10, resultSet.getInt("id"));
        Assert.assertEquals("Bu urun bitkisel yag ve 'Gluten' icermektedir.", resultSet.getString("description"));
        Assert.assertEquals("Acili ezme", resultSet.getString("name"));

        resultSet.next();
        Assert.assertTrue(resultSet.getBoolean("active"));
        Assert.assertEquals(5, resultSet.getInt("price"));
        Assert.assertEquals(11, resultSet.getInt("id"));
        Assert.assertEquals("Bu urun bitkisel yag ve 'Gluten' icermektedir.", resultSet.getString("description"));
        Assert.assertEquals("Yogun Baharatli ezme", resultSet.getString("name"));

    }

    @When("{string} sorgusu yapilir")
    public void sorgusuYapilir(String query) {
        resultSet = DBUtils.executeQuery(query);
        Assert.assertNotNull("Sorgu sonucu null döndü, bağlantı veya sorgu hatalı olabilir.", resultSet);
    }

    @When("{string} sorgusu gonderilir")
    public void sorgusuGonderilir(String query) {
        resultSet = DBUtils.executeQuery(query + TestData.expectedProductId);
        Assert.assertNotNull("Sorgu sonucu null döndü, bağlantı veya sorgu hatalı olabilir.", resultSet);
    }

    @Then("Urunun guncellendigi database'de kontrol edilir")
    public void urununGuncellendigiDatabaseDeKontrolEdilir() throws SQLException {
        resultSet.next();
        Assert.assertEquals(TestData.expectedProductName, resultSet.getString("name"));
        Assert.assertEquals(TestData.expectedContentsText, resultSet.getString("contents"));
        Assert.assertEquals(TestData.expectedDescriptionText, resultSet.getString("description"));
        Assert.assertEquals(TestData.expectedPriceText, resultSet.getString("price").toString());
        Assert.assertEquals(TestData.expectedDiscountText, resultSet.getString("discount").toString());

    }

    @Then("Urunun category idsi dogrulanir")
    public void urununCategoryIdsiDogrulanir() throws SQLException {
        resultSet.next();
        Assert.assertEquals(2, resultSet.getInt("category_id"));

    }

    @Then("Ayni categorye sahip urunlerin benzersiz product idye sahip oldugu dogrulanir")
    public void ayniCategoryeSahipUrunlerinBenzersizProductIdyeSahipOlduguDogrulanir() throws SQLException {
        resultSet.next();
        Assert.assertEquals(22, resultSet.getInt("product_id"));

        resultSet.next();
        Assert.assertEquals(21, resultSet.getInt("product_id"));

        resultSet.next();
        Assert.assertEquals(38, resultSet.getInt("product_id"));

        resultSet.next();
        Assert.assertEquals(20, resultSet.getInt("product_id"));

        resultSet.next();
        Assert.assertEquals(39, resultSet.getInt("product_id"));


        resultSet.next();
        Assert.assertEquals(23, resultSet.getInt("product_id"));


        resultSet.next();
        Assert.assertEquals(32, resultSet.getInt("product_id"));


        resultSet.next();
        Assert.assertEquals(40, resultSet.getInt("product_id"));


    }

    @Then("Urun bilgilerinin dogru oldugu kontrol edilir")
    public void urunBilgilerininDogruOlduguKontrolEdilir() throws SQLException {

        resultSet.next();
        Assert.assertTrue(resultSet.getBoolean("active"));
        Assert.assertTrue(resultSet.getBoolean("available"));
        Assert.assertTrue(resultSet.getBoolean("is_popular"));
        Assert.assertEquals(40, resultSet.getInt("price"));
        Assert.assertEquals(35, resultSet.getInt("order_quantity"));
        Assert.assertEquals("Seker, un", resultSet.getString("contents"));
        Assert.assertEquals("Seker, sivi yag, un", resultSet.getString("description"));
        Assert.assertEquals("Tatli 3333", resultSet.getString("name"));


    }

    @Then("Urunun silindigi database uzerinden kontrol edilir")
    public void urununSilindigiDatabaseUzerindenKontrolEdilir() throws SQLException {
        resultSet.next();
        Assert.assertNotEquals(TestData.expectedProductName, resultSet.getString("name"));
    }

    @When("Olusturulan Category icin {string} sorgusu yapilir")
    public void olusturulanCategoryIcinSorgusuYapilir(String query) {
        resultSet = DBUtils.executeQuery(query + "'" + TestData.expectedCategoryName + "'");
        Assert.assertNotNull("Sorgu sonucu null döndü, bağlantı veya sorgu hatalı olabilir.", resultSet);


    }

    @Then("Olusturulan categorynin database uzerinde gorunurlugu dogrulanir")
    public void olusturulanCategoryninDatabaseUzerindeGorunurluguDogrulanir() throws SQLException {
        resultSet.next();
        Assert.assertEquals(TestData.expectedCategoryName, resultSet.getString("name"));


    }

    @Then("Olusturulan categorynin database uzerinde silindigi dogrulanir")
    public void olusturulanCategoryninDatabaseUzerindeSilindigiDogrulanir() throws SQLException {
        resultSet.next();
        Assert.assertNotEquals(TestData.expectedCategoryName, resultSet.getString("name"));


    }

    @When("Olusturulan admin icin {string} sorgusu gonderilir")
    public void olusturulanAdminIcinSorgusuGonderilir(String query) {
        resultSet = DBUtils.executeQuery(query + "'" + TestData.userName + "'");
        Assert.assertNotNull("Sorgu sonucu null döndü, bağlantı veya sorgu hatalı olabilir.", resultSet);

    }

    @Then("adminin olusturuldugu database'de kontrol edilir")
    public void admininOlusturulduguDatabaseDeKontrolEdilir() throws SQLException {
        resultSet.next();
        Assert.assertEquals(TestData.userName,resultSet.getString("user_name"));
    }
}
