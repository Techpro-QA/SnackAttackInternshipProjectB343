package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import snackattack.utilities.ConfigReader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API_UserControllerStepDefs extends UserControllerBaseSteps {


    @Given("{string} endpoint'ine bağlantı kurulur ve geçerli token alınır")
    public void endpointIneBağlantıKurulurVeGeçerliTokenAlınır(String arg0) {

        String loginUrl = ConfigReader.getProperty("loginUrl");
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", "tester013@gmail.com");
        loginBody.put("password", "123456");

        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .post(loginUrl);

        token = response.jsonPath().getString("token");
        Assert.assertEquals(200, response.statusCode());
    }


    @When("Kullanıcı {string} endpoint'ine PATCH isteği gönderir")
    public void kullanıcıEndpointInePATCHIsteğiGönderir(String arg0) {
        String patchUrl = ConfigReader.getProperty("updateUserUrl");

        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("firstName", "UpdatedName");
        updateBody.put("lastName", "UpdatedSurname");
        updateBody.put("address", "UpdatedAddress");

        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(updateBody)
                .patch(patchUrl);

    }

    @Then("Status code {int} olmalı")
    public void statusCodeOlmalı(int arg0) {
        int expectedStatusCode = 54;
        Assert.assertEquals(expectedStatusCode, response.statusCode());

    }

    @And("Response body içinde güncellenmiş kullanıcı bilgileri doğrulanmalı")
    public void responseBodyIçindeGüncellenmişKullanıcıBilgileriDoğrulanmalı() {
        JsonPath json = response.jsonPath();
        Assert.assertEquals("UpdatedName", json.getString("object.firstName"));
        Assert.assertEquals("UpdatedSurname", json.getString("object.lastName"));
        Assert.assertEquals("UpdatedAddress", json.getString("object.address"));
    }


    @When("Kullanıcı {string} endpoint'ine GET isteği gönderir")
    public void kullanıcıEndpointIneGETIsteğiGönderir(String arg0) {
        String getUserUrl = ConfigReader.getProperty("getAuthenticatedUserUrl");
        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .get(getUserUrl);
    }


    @And("Response body içinde kullanıcı email, isim ve adres bilgileri doğrulanmalı")
    public void responseBodyIçindeKullanıcıEmailIsimVeAdresBilgileriDoğrulanmalı() {
        JsonPath json = response.jsonPath();
        Assert.assertEquals("testero13@gmail.com", json.getString("object.email"));
        Assert.assertEquals("Testero", json.getString("object.firstName"));
        Assert.assertEquals("Istanbul-codex", json.getString("object.address"));
    }


    @When("Kullanıcı getOrdersByAuthUser endpoint'ine GET isteği gönderir")
    public void kullanici_get_orders_istegi_gonderir() {
        String getOrdersUrl = ConfigReader.getProperty("getOrdersByAuthUserUrl");

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .get(getOrdersUrl);
    }



    @And("Response body içinde sipariş listesi boş veya dolu olarak doğrulanmalı")
    public void responseBodyIçindeSiparişListesiBoşVeyaDoluOlarakDoğrulanmalı() {
        JsonPath json = response.jsonPath();
        List<Map<String, Object>> orders = json.getList("content");

        if (orders.isEmpty()) {
            System.out.println("Sipariş listesi boş.");
            Assert.assertTrue(json.getBoolean("empty"));
        } else {
            System.out.println("Sipariş listesi dolu.");
            Assert.assertFalse(json.getBoolean("empty"));
            Assert.assertTrue(json.getInt("totalElements") > 0);
        }

    }

}

