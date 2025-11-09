package snackattack.stepdefs.db_stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import snackattack.utilities.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DB_UserRoleStepDefs {
    public static ResultSet resultSet;
    boolean hasDuplicate = false;

    @When("Kullanici {string}tablosundaki tüm kayıtları sorgular")
    public void kullaniciTablosundakiTümKayıtlarıSorgular(String user_roles) {
        String query = "SELECT * FROM " + user_roles;
        resultSet = DBUtils.executeQuery(query);

    }

    @Then("Role tablosu aşağıdaki verileri içermelidir:")
    public void roleTablosuAşağıdakiVerileriIçermelidir() throws SQLException {
        DataTable dataTable = null;
        List<List<String>> expectedData = dataTable.asLists(String.class);

        List<List<String>> actualData = new ArrayList<>();

        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(resultSet.getInt("id")));
            row.add(resultSet.getString("role_type"));
            row.add(resultSet.getString("role_name"));
            actualData.add(row);
        }

        // Beklenen ve actual verileri karşılaştır
        Assert.assertEquals("Role tablosu beklenen verileri içermiyor!", expectedData, actualData);
    }




    @When("Kullanıcı kayıtlı olmayan {string} role_type değerine sahip kaydı sorgular")
    public void kullanıcıKayıtlıOlmayanRole_typeDeğerineSahipKaydıSorgular(String roleType) {
        String query = "SELECT * FROM role WHERE role_type = '" + roleType + "'";
        resultSet = DBUtils.executeQuery(query);
    }

    @Then("Herhangi bir kayıt dönmemelidir")
    public void herhangi_bir_kayit_donmemelidir() throws SQLException {
        Assert.assertFalse("Beklenmeyen kayıt bulundu!", resultSet.next());
    }


    @When("Kullanıcı role_type değerlerini sorgular")
    public void kullanici_role_type_degerlerini_sorgular() throws SQLException, InterruptedException {
        String query = "SELECT role_type FROM role";
        ResultSet rs = DBUtils.executeQuery(query);

        Set<String> roleTypes = new HashSet<>();
        boolean hasDuplicate = false;

        while (rs.next()) {
            String roleType = rs.getString("role_type");
            if (!roleTypes.add(roleType)) {
                hasDuplicate = true;
                break; // İlk tekrar bulunduğunda döngüden çık
            }
        }

        Assert.assertFalse("Veritabanında tekrar eden role_type bulundu!", hasDuplicate);

    }


    @Then("Her role_type yalnızca bir kez bulunmalıdır")
    public void herRole_typeYalnızcaBirKezBulunmalıdır() {
        Assert.assertFalse("Yinelenen role_type bulundu!", hasDuplicate);

    }


}




