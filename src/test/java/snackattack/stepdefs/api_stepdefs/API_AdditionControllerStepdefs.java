package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import snackattack.pojos.AdditionRequestPojo;
import snackattack.stepdefs.Hook;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class API_AdditionControllerStepdefs {

    private Response response;
    private AdditionRequestPojo additionRequestPojo;
    private static int createdAdditionId;

    //Status kontrolü
    @Then("Gelen Status kodu {int} olmalı")
    public void statusKoduOlmali(int statusCode) {
        int actualStatus = response.statusCode();
        if (actualStatus != statusCode) {
            System.err.println("Beklenen status " + statusCode + " ama dönen: " + actualStatus);
            System.err.println("Response Body: " + response.getBody().asString());
        } else {
            System.out.println("Status code doğru: " + statusCode);
        }

    }
    //GET / POST  / PUT
    @When("Admin {string} isteğini {string} endpointine gönderir")
    public void kullaniciIstegiGonderir(String method, String endpoint){
        if (method.equalsIgnoreCase("GET")) {
            response = given()
                    .spec(Hook.spec)
                    .when()
                    .get(endpoint);

        } else if (method.equalsIgnoreCase("POST")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .body(additionRequestPojo)
                    .when()
                    .post(endpoint);
           // createdAdditionId = response.jsonPath().getInt("data.id");     // Oluşan ID'yi sakla
            createdAdditionId = response.jsonPath().getInt("data.id");
            System.out.println("Kaydedilen addition ID: " + createdAdditionId);

            System.out.println("POST Response Body: " + response.getBody().asString());
        } else if (method.equalsIgnoreCase("PUT")) {
            response = given()
                    .spec(Hook.spec)
                    .contentType(ContentType.JSON)
                    .body(additionRequestPojo)
                    .when()
                    .put(endpoint);
            System.out.println("PUT Response Body: " + response.getBody().asString());

        }
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString().substring(0, Math.min(300, response.getBody().asString().length())));
    }

    // ==================== PUT ====================
    @Given("Admin güncellenecek addition'i hazırlar")
    public void adminGuncellenecekAdditioniHazirlar() {
        AdditionRequestPojo.CategoryRequest category = new AdditionRequestPojo.CategoryRequest();
        category.setId(2);
        category.setCreatedAt("2025-11-06T19:13:23.449Z");
        category.setUpdatedAt("2025-11-06T19:13:23.449Z");
        category.setName("Salata Soslari");
        category.setActive(true);

        additionRequestPojo = new AdditionRequestPojo();
        additionRequestPojo.setId(8); // Backend’de var olan addition ID
        additionRequestPojo.setCreatedAt("2025-11-06T19:13:23.449Z");
        additionRequestPojo.setUpdatedAt("2025-11-06T19:13:23.449Z");
        additionRequestPojo.setName("Barbekü sos");
        additionRequestPojo.setDescription("Tatlı-ekşi ve isli aroma");
        additionRequestPojo.setPrice(5.0);
        additionRequestPojo.setActive(true);
        additionRequestPojo.setCategoryRequest(category);
        additionRequestPojo.setProductId(0);
        additionRequestPojo.setRemoveIngredients(Collections.singletonList("string"));
        additionRequestPojo.setAddExtras(Collections.singletonList("string"));

        System.out.println("PUT için addition hazırlandı: " + additionRequestPojo.getName());
    }


    @And("Addition güncellendigi dogrulanir")
    public void additionGüncellendigiDogrulanir() {
        assertThat(response.jsonPath().getBoolean("success"), is(true));
        assertThat(response.jsonPath().getString("message"), equalTo("Addition updated successfully"));
        assertThat(response.jsonPath().getInt("data.id"), equalTo(additionRequestPojo.getId()));
        assertThat(response.jsonPath().getString("data.name"), equalTo(additionRequestPojo.getName()));
        assertThat(response.jsonPath().getString("data.description"), equalTo(additionRequestPojo.getDescription()));
        assertThat(response.jsonPath().getFloat("data.price"), equalTo((float) additionRequestPojo.getPrice()));
        assertThat(response.jsonPath().getBoolean("data.active"), is(additionRequestPojo.isActive()));
        assertThat(response.jsonPath().getString("data.additionCategoryName"), equalTo(additionRequestPojo.getCategoryRequest().getName()));
    }

    // ==================== POST ====================
    @Given("Yeni bir addition hazırlar")
    public void yeniBirAdditionHazirlar() {
        AdditionRequestPojo.CategoryRequest category = new AdditionRequestPojo.CategoryRequest();
        category.setId(3);
        category.setCreatedAt("2025-11-06T18:57:31.849Z");
        category.setUpdatedAt("2025-11-06T18:57:31.849Z");
        category.setName("Mezeler");
        category.setActive(true);

        additionRequestPojo = new AdditionRequestPojo();
        additionRequestPojo.setId(99);
        additionRequestPojo.setCreatedAt("2025-11-06T18:57:31.849Z");
        additionRequestPojo.setUpdatedAt("2025-11-06T18:57:31.849Z");
        additionRequestPojo.setName("Ali Nazik");
        additionRequestPojo.setDescription("Patlican ve yogurt icerir");
        additionRequestPojo.setPrice(7.0);
        additionRequestPojo.setActive(true);
        additionRequestPojo.setCategoryRequest(category);
        additionRequestPojo.setProductId(0);
        additionRequestPojo.setRemoveIngredients(Collections.singletonList("string"));
        additionRequestPojo.setAddExtras(Collections.singletonList("string"));

        System.out.println("POST için addition hazırlandı: " + additionRequestPojo.getName());
    }


    @And("Addition başarıyla oluşturulmalı")
    public void additionBasariylaOlusturulmali() {
        assertThat(response.jsonPath().getBoolean("success"), is(true));
        assertThat(response.jsonPath().getString("data.name"), equalTo(additionRequestPojo.getName()));
        assertThat(response.jsonPath().getFloat("data.price"), equalTo((float) additionRequestPojo.getPrice()));
        System.out.println("Addition başarıyla oluşturuldu: " + additionRequestPojo.getName() + ", ID: " + createdAdditionId);
    }

//    @And ("Gelen id değeri kaydedilir")
//    public void gelenIdDegeriKaydedilir() {
//
//            createdAdditionId = response.jsonPath().getInt("data.id");
//            System.out.println("Kaydedilen addition ID: " + createdAdditionId);
//
//    }

    // ==================== DELETE ====================
    @When("Kullanıcı bir addition siler")
    public void kullaniciBirAdditionSiler() {
        String endpoint = "/addition/" + createdAdditionId;

        response = given()
                .spec(Hook.spec)
                .when()
                .delete(endpoint);

        System.out.println("DELETE Endpoint: " + endpoint);
        System.out.println("DELETE Status Code: " + response.getStatusCode());
        System.out.println("DELETE Response Body: " + response.getBody().asString());
    }

}
