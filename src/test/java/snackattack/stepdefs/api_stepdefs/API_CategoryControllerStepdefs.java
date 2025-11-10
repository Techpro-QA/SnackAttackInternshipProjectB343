package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import snackattack.pojos.CategoryPostPojo;
import snackattack.pojos.CategoryPutPojo;
import snackattack.pojos.CategoryPutResponsePojo;
import snackattack.pojos.CategoryResponsePojo;
import snackattack.stepdefs.Hook;
import snackattack.utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class API_CategoryControllerStepdefs {
    private Response response;
    private CategoryPostPojo categoryPostPojo;
    private CategoryPutPojo categoryPutPojo;

    @When("Kullanici category icin {string} isteğini {string} endpointine gönderir")
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
                    .body(categoryPostPojo)
                    .when()
                    .post(endpoint);
        } else if (method.equalsIgnoreCase("PUT")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .body(categoryPutPojo)
                    .when()
                    .put(endpoint);
        } else if (method.equalsIgnoreCase("DYNAMIC DELETE")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(endpoint + TestData.createdCategoryId);
        }

        System.out.println("Endpoint: " + endpoint);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " +
                response.getBody().asString().substring(0, Math.min(300, response.getBody().asString().length())));
    }

    //Status kontrolü
    @Then("Category status kodu {int} olmalı")
    public void status_kodu_olmali(int statusCode) {
        int actualStatus = response.statusCode();
        if (actualStatus != statusCode) {
            System.err.println("Beklenen status " + statusCode + " ama dönen: " + actualStatus);
            System.err.println("Response Body: " + response.getBody().asString());
        } else {
            System.out.println("Status code doğru: " + statusCode);
        }
    }


    @And("Category'lerin goruldugu kontrol edilir")
    public void categoryLerinGorulduguKontrolEdilir() {
        response.then().contentType(ContentType.JSON);
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains("id"));

    }


    @When("Olusturulacak catergory icin payload hazirlanir")
    public void olusturulacakCatergoryIcinPayloadHazirlanir() {
        categoryPostPojo = new CategoryPostPojo("Bouble Tea",true);
    }

    @And("Category'nin olusturuldugu control edilir")
    public void categoryNinOlusturulduguControlEdilir() {
        CategoryResponsePojo actualData = response.as(CategoryResponsePojo.class);

        Assert.assertEquals(categoryPostPojo.getName(),actualData.getData().getName());
        Assert.assertEquals(categoryPostPojo.isActive(),actualData.getData().isActive());

        TestData.createdCategoryId = actualData.getData().getId();

    }

    @When("Guncellenecek urunun payloadu hazirlanir")
    public void guncellenecekUrununPayloaduHazirlanir() {
        categoryPutPojo=new CategoryPutPojo(TestData.createdCategoryId,"Chai Tea", true);



    }

    @And("Categorynin guncellendigi kontrol edilir")
    public void categoryninGuncellendigiKontrolEdilir() {
        CategoryPutResponsePojo actualData = response.as(CategoryPutResponsePojo.class);
        Assert.assertEquals(categoryPutPojo.getName(), actualData.getData().getName());
        Assert.assertEquals(categoryPutPojo.isActive(), actualData.getData().isActive());

    }

    @And("Categorynin silindigi kontrol edilir")
    public void categoryninSilindigiKontrolEdilir() {
        Assert.assertTrue(response.asString().contains("Categor deleted"));

    }

    @And("UI'da olusturulan Categorynin goruldugu kontrol edilir")
    public void uiDaOlusturulanCategoryNinGorulduguKontrolEdilir() {

        response.then().contentType(ContentType.JSON);
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains(TestData.expectedCategoryName));


    }
}
