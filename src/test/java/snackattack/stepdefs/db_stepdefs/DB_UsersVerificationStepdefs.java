package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.DBUtils;
import snackattack.utilities.TestData;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DB_UsersVerificationStepdefs {
    private ResultSet rs;

    @When("Kullanici config dosyasindaki email adresine sahip kaydi sorgular")
    public void kullaniciConfigDosyasindakiEmailAdresineSahipKaydiSorgular() {
        String email = ConfigReader.getProperty("memoryEmail");
        String query =  "SELECT * FROM snack_attack_db.users WHERE email = '" + email + "'";
        rs = DBUtils.executeQuery(query);
    }

    @Then("Kullanicinin veritabanindaki bilgileri config dosyasindaki datalarla uyusmalidir")
    public void kullanicininVeritabanindakiBilgileriConfigDosyasindakiDatalarlaUyusmalidir() throws SQLException {
        boolean hasRecord = rs!= null && rs.next();
        assertTrue("Kullanici veri tabaninda bulunamadi!", hasRecord);
        //Config dosyasindaki degerleri aliyoruz
        String expectedFirstName = ConfigReader.getProperty("memoryFirstName");
        String expectedLastName = ConfigReader.getProperty("memoryLastName");
        String expectedUserName = ConfigReader.getProperty("memoryUserName");
        String expectedEmail = ConfigReader.getProperty("memoryEmail");
        String expectedPhoneNumber = ConfigReader.getProperty("memoryPhoneNumber");
        //DB den gelenleri aliyoruz
        String actualFirstName = rs.getString("first_name");
        String actualLastName  = rs.getString("last_name");
        String actualUserName  = rs.getString("user_name");
        String actualEmail     = rs.getString("email");
        String actualPhoneRaw  = rs.getString("phone_number"); // null olabilir

        // Normalize telefon => tum rakamlari al
        String actualPhone = actualPhoneRaw == null ? "" : actualPhoneRaw.replaceAll("\\D", "");
        String expectedPhoneNormalized = expectedPhoneNumber == null ? "" : expectedPhoneNumber.replaceAll("\\D", "");

        assertEquals("First Name uyusmuyor!", expectedFirstName, actualFirstName);
        assertEquals("Last Name uyusmuyor!", expectedLastName, actualLastName);
        assertEquals("User Name uyusmuyor!", expectedUserName, actualUserName);
        assertEquals("Email uyusmuyor!", expectedEmail, actualEmail);
        assertEquals("Telefon numarasi uyusmuyor!", expectedPhoneNormalized, actualPhone);

        System.out.println(" Kullanici veri tabaninda dogrulandi " + actualEmail);

    }

    @When("Kullanici kayitli olmayan {string} email adresine sahip kaydi sorgular")
    public void kullaniciKayitliOlmayanEmailAdresineSahipKaydiSorgular(String email) {
        String query =  "SELECT * FROM snack_attack_db.users WHERE email = '" + email + "'";
        System.out.println("Sorgu calistirildi = " + query);
        rs = DBUtils.executeQuery(query);
    }

    @Then("Herhangi bir kayit donmemelidir")
    public void herhangiBirKayitDonmemelidir() throws SQLException {
        boolean kayitVarMi = rs.next();//herhangibir satir donuyor mu
        assertFalse("Beklenmeyen sekilde kullanici bulundu", kayitVarMi);
    }

    @When("Kullanici users tablosundaki email adreslerini gruplayarak sorgular")
    public void kullaniciUsersTablosundakiEmailAdresleriniGruplayarakSorgular() {
        String query = "SELECT email, COUNT(*) AS adet " +
                "FROM snack_attack_db.users " +
                "GROUP BY email " +
                "HAVING COUNT(*) > 1";
        System.out.println(" Duplicate kontrol sorgusu: " + query);
        rs = DBUtils.executeQuery(query);
    }

    @Then("Her email adresi yalnizca bir kez bulunmalidir")
    public void herEmailAdresiYalnizcaBirKezBulunmalidir() throws SQLException {
        boolean duplicateVarMi = rs.next();//herhangibir sonuc donerse duplicate email var demektir

        assertFalse("User tablosunda ayni email birden fazla kayitli!", duplicateVarMi);

        System.out.println("Tum email adresleri veritabaninda unique");

    }

    @When("Kullanici TestData’daki email bilgisiyle veritabaninda kaydi sorgular")
    public void kullaniciTestDataDakiEmailBilgisiyleVeritabanindaKaydiSorgular() {
        String email = TestData.email;
        String query = "SELECT * FROM snack_attack_db.users WHERE email = '" + email + "'";
        System.out.println("Sorgu calistiriliyor = " + query);
        rs = DBUtils.executeQuery(query);
    }

    @Then("Kullanicinin veritabanindaki bilgileri TestData’daki data ile uyusmalidir")
    public void kullanicininVeritabanindakiBilgileriTestDataDakiDatalarlaUyusmalidir() throws SQLException {
        assertNotNull("Resultset null dondu,sorgu calistirilmamis olabilir",rs);
        assertTrue("Kullanici veri tabaninda bulunamadi", rs.next());

        //DB'den gelen veriler
        String actualFirstName = rs.getString("first_name");
        String actualLastName  = rs.getString("last_name");
        String actualUserName  = rs.getString("user_name");
        String actualEmail     = rs.getString("email");
        String actualPhoneRaw  = rs.getString("phone_number");

        //Test datadan gelen veriler =>Bunlar ui testinde olusturdugumuz dinamik datalar
        String expectedFirstName = TestData.firstName;
        String expectedLastName  = TestData.lastName;
        String expectedUserName  = TestData.userName;
        String expectedEmail     = TestData.email;
        String expectedPhone     = TestData.phoneNumber;

        //Assertions
        assertEquals(expectedFirstName,actualFirstName);
        assertEquals(expectedLastName,actualLastName);
        assertEquals(expectedUserName,actualUserName);
        assertEquals(expectedEmail,actualEmail);
        assertEquals(expectedPhone,actualPhoneRaw);

        System.out.println("Kullanici DB'de basariyla dogrulandi = " + actualEmail);

    }
}
