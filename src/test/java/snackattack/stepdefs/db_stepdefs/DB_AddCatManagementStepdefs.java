package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.utilities.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DB_AddCatManagementStepdefs {
    public static Map<String, Object> actualDatabaseU = new HashMap<>();
        @When("additions_category tablosu veritabaninda aranir")
        public void additions_categoryTablosuVeritabanindaAranir() throws SQLException {
            ResultSet resultSet = DBUtils.executeQuery("SELECT * FROM snack_attack_db.additions_category");

            if (resultSet.next()) {
                actualDatabaseU.put("id", resultSet.getInt("id"));
                actualDatabaseU.put("name", resultSet.getString("name"));
                actualDatabaseU.put("active", resultSet.getString("active"));
            }
            System.out.println("actualDatabaseU = " + actualDatabaseU);
        }

    @Then("additions_category bilgileri veritabaninda bulunmalidir")
    public void additions_categoryBilgileriVeritabanindaBulunmalidir() {

        Assert.assertEquals(1,actualDatabaseU.get("id"));
        Assert.assertEquals("Soslar",actualDatabaseU.get("name"));
        Assert.assertEquals("t",actualDatabaseU.get("active"));
    }
}
