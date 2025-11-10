package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.*;
import org.junit.Assert;

import java.sql.*;

public class DB_Image_EntityStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;


    @Then("Tablo sonucunun boş olduğu doğrulanır")
    public void tablo_sonucunun_bos_oldugu_dogrulanir() throws SQLException {
        if (resultSet.next()) {
            System.out.println("❌ Tablo boş değil.");
            Assert.fail("image_entity tablosu boş değil!");
        } else {
            System.out.println("✅ Tablo boş.");
        }

        // Bağlantıyı kapatalım
        resultSet.close();
        statement.close();
        connection.close();
    }

}
