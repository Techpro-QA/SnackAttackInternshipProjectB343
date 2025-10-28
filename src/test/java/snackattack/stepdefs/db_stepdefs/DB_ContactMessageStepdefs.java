package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_ContactMessageStepdefs {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @When("Contact message tablosu veritabaninda aranir")
    public void contactMessageTablosuVeritabanindaAranir() throws SQLException {

        statement = connection.createStatement();

        resultSet = statement.executeQuery("select * from contact_message");

        resultSet.next();

        resultSet.getString("name");


    }

    @Then("UI'deki tum contact message bilgileri veritabanindaki kayitlarla ayni olmali")
    public void uiDekiTumContactMessageBilgileriVeritabanindakiKayitlarlaAyniOlmali() {



    }
}
