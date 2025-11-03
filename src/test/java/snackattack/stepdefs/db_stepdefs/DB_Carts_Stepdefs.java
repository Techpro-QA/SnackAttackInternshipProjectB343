package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.utilities.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Carts_Stepdefs {

    @Then("UI'daki ilk cart bilgileri veritabanındaki kayıtla aynı olmalıdır")
    public void uiDakiIlkCartBilgileriVeritabanındakiKayıtlaAynıOlmalıdır() throws SQLException {

        Map<String, Object> expectedUIData = new HashMap<>();
        expectedUIData.put("id", 1);
        expectedUIData.put("total_amount", 0);
        expectedUIData.put("user_id", 6);
        expectedUIData.put("status", "ACTIVE");

        String query = "SELECT * FROM snack_attack_db.carts WHERE id = 1;";
        ResultSet resultSet1 = DBUtils.executeQuery(query);

        if (resultSet1.next()) {

            Assert.assertEquals(expectedUIData.get("id"), resultSet1.getInt("id"));
            Assert.assertEquals(expectedUIData.get("total_amount"), resultSet1.getInt("total_amount"));
            Assert.assertEquals(expectedUIData.get("user_id"), resultSet1.getInt("user_id"));
            Assert.assertEquals(expectedUIData.get("status"), resultSet1.getString("status"));
        } else {
            Assert.fail("Sorgudan hiç kayıt dönmedi!");
        }

        DBUtils.closeConnection();
    }
}
