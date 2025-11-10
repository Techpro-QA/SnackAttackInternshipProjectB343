package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import snackattack.pojos.CartItemPojo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static snackattack.stepdefs.Hook.spec;


public class API_CartControllerStepdefs {
    String guestToken;
    int expectedProductId;
    int expectedQuantity;
    int itemId;
    Response response;
    Response getResponse;

    @Given("kullanıcı geçerli bir Guest Token'a sahiptir")
    public void kullanici_gecerli_bir_guest_tokena_sahiptir() {
        guestToken= "f9235afd-70fa-48eb-93d9-445dae221d26";
        System.out.println("Statik Guest Token kullanılıyor = " + guestToken);
    }

    @Given("sepet boşaltılır")
    public void sepet_bosaltilir() {

        getResponse = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .when()
                .get("/carts/current")
                .then()
                .extract()
                .response();

        List<Object> items = getResponse.jsonPath().getList("data.items");

        if (items != null && !items.isEmpty()) {
            itemId = getResponse.jsonPath().getInt("data.items[0].id");

            given()
                    .spec(spec)
                    .header("X-Guest-Token", guestToken)
                    .when()
                    .delete("/carts/current/items/" + itemId)
                    .then()
                    .statusCode(200);

            System.out.println("🧹 Sepet boşaltıldı ✅");

        } else {
            System.out.println("🟢 Sepet zaten boş.");
        }
    }

    @When("kullanıcı sepete productId {string} ve quantity {string} ile ürün ekler")
    public void kullanici_sepete_product_id_ve_quantity_ile_urun_ekler(String id, String adet) {

        expectedProductId = Integer.parseInt(id);
        expectedQuantity = Integer.parseInt(adet);

        CartItemPojo item = new CartItemPojo(expectedProductId, expectedQuantity, 10, 10, Collections.emptyList());

        response = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .body(item)
                .when()
                .post("/carts/current/items")
                .then()
                .extract()
                .response();

        assertEquals(200, response.statusCode());

        // ✅ Ürün ekledikten sonra sepet yeniden çekilir
        getResponse = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .when()
                .get("/carts/current")
                .then()
                .extract()
                .response();

        // ✅ itemId güncel sepetten alınır
        itemId = getResponse.jsonPath().getInt("data.items[0].id");
    }

    @Then("API yanıt kodu 200 olmalı")
    public void yanit_200() {
        assertEquals(200, response.getStatusCode());
    }

    @And("kullanıcı sepette eklenen ürünün göründüğünü doğrular")
    public void sepet_dogrulama() {
        int actualId = getResponse.jsonPath().getInt("data.items[0].productId");
        int actualQty = getResponse.jsonPath().getInt("data.items[0].quantity");

        assertEquals(expectedProductId, actualId);
        assertEquals(expectedQuantity, actualQty);
    }

    @And("kullanıcı sepetteki ürünün miktarını {string} olarak günceller")
    public void urun_miktari_guncelle(String yeniAdet) {
        expectedQuantity = Integer.parseInt(yeniAdet);

        Map<String, Object> body = new HashMap<>();
        body.put("newQuantity", expectedQuantity);

        response = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .body(body)
                .when()
                .put("/carts/current/items/" + itemId)
                .then()
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
    }

    @Then("kullanıcı sepette ürün miktarının güncellenmiş olduğunu doğrular")
    public void miktar_dogrula() {
        Response check = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .when()
                .get("/carts/current")
                .then()
                .extract()
                .response();

        int actualQty = check.jsonPath().getInt("data.items[0].quantity");
        assertEquals(expectedQuantity, actualQty);
    }

    @And("kullanıcı sepetteki ürünü siler")
    public void urun_sil() {
        response = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .when()
                .delete("/carts/current/items/" + itemId)
                .then()
                .extract()
                .response();
        assertEquals(200, response.getStatusCode());
    }

    @Then("sepet boş olmalı")
    public void sepet_bos_olmalı() {
        Response check = given()
                .spec(spec)
                .header("X-Guest-Token", guestToken)
                .when()
                .get("/carts/current")
                .then()
                .extract()
                .response();

        List<Object> items = check.jsonPath().getList("data.items");
        assertTrue(items == null || items.isEmpty());
    }

}


