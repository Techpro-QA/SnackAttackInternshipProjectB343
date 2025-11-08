package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import snackattack.utilities.DBUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DB_Cart_item_additionsStepdefs {

    @Then("Cart_item_additions tablo veri eslesmeleri dogrulanir {string}")
    public void cart_item_additionsTabloVeriEslesmeleriDogrulanir(String values) throws SQLException {
        ResultSet resultSet = DBUtils.statement.getResultSet();
        Assert.assertNotNull("ResultSet NULL geldi! Sorgu çalışmamış olabilir.", resultSet);

        //  column ismi dinamik olarak alınır
        String columnName = resultSet.getMetaData().getColumnName(1);

        List<Integer> actualValues = new ArrayList<>();

        while (resultSet.next()) {
            actualValues.add(resultSet.getInt(columnName));
        }

        List<Integer> expected = Arrays.stream(values.split(","))
                .map(v -> Integer.parseInt(v.trim()))
                .toList();

        Assert.assertTrue("Tablo veri eşleşmesi hatalı!", actualValues.containsAll(expected));

        System.out.println("✅ Doğrulama başarılı. Kullanılan kolon adı: " + columnName);
    }
}
