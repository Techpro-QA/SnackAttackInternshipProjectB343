package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import snackattack.utilities.DBUtils;

public class DataBaseStepdefs {

    @Given("Veritabanı bağlantısı kurulur")
    public void veritabanıBağlantısıKurulur() {
        DBUtils.createConnection();
    }

    @And("Baglanti kapatilir")
    public void baglantiKapatilir() {
        DBUtils.closeConnection();
    }

}
