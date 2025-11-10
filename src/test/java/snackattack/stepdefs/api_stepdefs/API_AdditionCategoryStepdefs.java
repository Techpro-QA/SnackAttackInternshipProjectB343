package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import snackattack.pojos.AdditionCategoryPutPojo;
import snackattack.pojos.AdditonCategoryPostPojo;
import snackattack.pojos.AdditonCategoryPostResponsePojo;
import snackattack.stepdefs.Hook;
import snackattack.utilities.TestData;

import static io.restassured.RestAssured.given;

public class API_AdditionCategoryStepdefs {

    private Response response;
    private AdditonCategoryPostPojo additonCategoryPostPojo;
    private AdditionCategoryPutPojo additionCategoryPutPojo;

    //GET / POST istekleri / PUT
    @When("Kullanıcı addition category icin {string} isteğini {string} endpointine gönderir")
    public void kullanici_istegi_gonderir(String method, String endpoint) {

        if (method.equalsIgnoreCase("GET")) {
            response = given()
                    .spec(Hook.spec)
                    .when()
                    .get(endpoint);
        } else if (method.equalsIgnoreCase("POST")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .body(additonCategoryPostPojo)
                    .when()
                    .post(endpoint);
        } else if (method.equalsIgnoreCase("PUT")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .body(additionCategoryPutPojo)
                    .when()
                    .put(endpoint);
        } else if (method.equalsIgnoreCase("DELETE")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(endpoint);
        } else if (method.equalsIgnoreCase("DYNAMIC DELETE")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(endpoint + TestData.createdAdditonCategoryId);
        }

        System.out.println("Endpoint: " + endpoint);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " +
                response.getBody().asString().substring(0, Math.min(300, response.getBody().asString().length())));
    }

    //Status kontrolü
    @Then("Addition category icin Status kodu {int} olmalı")
    public void status_kodu_olmali(int statusCode) {
        int actualStatus = response.statusCode();
        if (actualStatus != statusCode) {
            System.err.println("Beklenen status " + statusCode + " ama dönen: " + actualStatus);
            System.err.println("Response Body: " + response.getBody().asString());
        } else {
            System.out.println("Status code doğru: " + statusCode);
        }

    }

    @Given("Yeni bir addition category icin payload hazırlanır")
    public void yeniBirAdditionCategoryIciPayloadHazırlanır() {
        additonCategoryPostPojo = new AdditonCategoryPostPojo("umut",true);
    }


    @And("Addition Category'nin olusturuldugu kontrol edilir")
    public void additionCategoryNinOlusturulduguKontrolEdilir() {

        AdditonCategoryPostResponsePojo actualData = response.as(AdditonCategoryPostResponsePojo.class);

        TestData.createdAdditonCategoryId = response.jsonPath().getInt("data.id");

        Assert.assertEquals(additonCategoryPostPojo.getName(),actualData.getData().getName());
        Assert.assertEquals(additonCategoryPostPojo.isActive(),actualData.getData().isActive());
    }

    @And("Addition Category'nin api uzerinde silindigi kontrol edilir")
    public void urununApiUzerindeSilindigiKontrolEdilir() {
        Assert.assertTrue(response.asString().contains("AdditionCategory deleted"));
    }

    @Then("Addition Category listesi dönmeli")
    public void urun_listesi_donmeli() {
        response.then().contentType(ContentType.JSON);
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains("id"));
        TestData.createdProductId = response.jsonPath().getInt("data.id");
    }

    @When("Yeni bir addition category icin put payload hazırlanır")
    public void yeniBirAdditionCategoryIcinPutPayloadHazırlanır() {
        additionCategoryPutPojo = new AdditionCategoryPutPojo(TestData.createdAdditonCategoryId,"osman",true);
    }

    @And("Addition Category'nin güncellendigi kontrol edilir")
    public void additionCategoryNinGüncellendigiKontrolEdilir() {

        AdditonCategoryPostResponsePojo actualData = response.as(AdditonCategoryPostResponsePojo.class);

        Assert.assertEquals(additionCategoryPutPojo.getName(),actualData.getData().getName());
        Assert.assertEquals(additionCategoryPutPojo.isActive(),actualData.getData().isActive());
    }
}
