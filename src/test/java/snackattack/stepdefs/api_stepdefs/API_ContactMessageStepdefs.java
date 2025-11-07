package snackattack.stepdefs.api_stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import snackattack.pojos.ContactMessagePostPojo;
import snackattack.stepdefs.Hook;
import snackattack.utilities.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static snackattack.stepdefs.Hook.spec;



public class API_ContactMessageStepdefs {

    public static Response response;
    LocalDate beginDate;
    LocalDate endDate;
    int lastId;

    //============POST REQUEST=====================

    @And("ContactMessages {string} endpoint'ine baglanti kurulur")
    public void contactmessagesEndpointIneBaglantiKurulur(String endPoint) {
        spec.pathParams("first", "contactMessages", "second", endPoint);
    }

    @When("Yeni bir contact message asagıdaki bilgilerle gonderilir")
    public void yeniBirContactMessageAşağıdakiBilgilerleGönderilir(io.cucumber.datatable.DataTable dataTable) {

        // DataTable → Map'e dönüştürülür
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = dataList.getFirst();

        // POJO nesnesi oluşturulur
        ContactMessagePostPojo requestBody = new ContactMessagePostPojo(
                data.get("name"),
                data.get("email"),
                data.get("subject"),
                data.get("message")
        );

        // POST isteği gönderilir
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/{first}/{second}");
        response.prettyPrint();
    }


    @Then("Statüs kodu {int} olmali")
    public void statüsKoduOlmali(int statusCode) {
        response.then().statusCode(statusCode);

    }

    @And("Response içinde {string} alanının {string} icerdigi dogrulanır")
    public void responseIçindeAlanıIçermeli(String alan, String beklenenDeger) {
        Object actualValue = response.jsonPath().get(alan);

        if (actualValue instanceof java.util.List) {
            response.then().body(alan, hasItem(beklenenDeger));
        } else if (actualValue != null) {
            response.then().body(alan, containsString(beklenenDeger));
        } else {
            throw new AssertionError("Response içinde '" + alan + "' alanı bulunamadı.");
        }
    }


    //============GET SEARCH BY SUBJECT REQUEST=====================

    @When("{string} olarak {string} bilgisi gönderilir")
    public void olarakBilgisiGönderilir(String field, String value) {

        response = given(spec)
                .queryParam(field.toLowerCase(), value)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .queryParam("sort", "dateTime")
                .queryParam("type", "desc")
                .when()
                .get("/{first}/{second}");


        response.prettyPrint();
    }


    //============GET searchBetweenTimes REQUEST=====================

    @When("Saat araligi parametreleri asagidaki gibi gonderilir")
    public void saatAraligiParametreleriAsagidakiGibiGonderilir(DataTable dataTable) {


        Map<String, String> data = dataTable.asMaps().get(0);

        response = given()
                .baseUri("http://207.154.209.12:8080")
                .basePath("/contactMessages/searchBetweenTimes")
                .header("Authorization", "Bearer " + Authentication.generateAdminToken())
                .queryParam("startHour", data.get("startHour"))
                .queryParam("startMinute", data.get("startMinute"))
                .queryParam("endHour", data.get("endHour"))
                .queryParam("endMinute", data.get("endMinute"))
                .when()
                .get();
    }

    @And("Response body içinde gelen bilgiler verilen saat aralığına uygun olmalı")
    public void responseBodyIçindeGelenBilgilerVerilenSaatAralığınaUygunOlmalı() {

        JsonPath jsonPath = response.jsonPath();
        response.prettyPrint();
        Object body = jsonPath.get("$");
        List<Map<String, Object>> responseList = new ArrayList<>();
        if (body instanceof List) {
            responseList = jsonPath.getList("$");
        } else if (body instanceof Map) {
            responseList.add((Map<String, Object>) body);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (Map<String, Object> item : responseList) {
            String actualDateTime = item.get("dateTime").toString();
            LocalDateTime dateTime = LocalDateTime.parse(actualDateTime, formatter);
            int hour = dateTime.getHour();
            int minute = dateTime.getMinute();

        }

    }

    //============GET searchBetweenDates REQUEST=====================


    @When("Kullanici {string} ile {string} tarihleri arasindaki verileri ister")
    public void kullaniciIleTarihleriArasindakiVerileriIster(String start, String end) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        beginDate = LocalDate.parse(start, formatter);
        endDate = LocalDate.parse(end, formatter);

        response = given(spec)
                .queryParam("beginDate", start)
                .queryParam("endDate", end)
                .when()
                .get("/{first}/{second}");

        response.prettyPrint();

    }

    @And("Response body içinde gelen bilgiler verilen gun aralığına uygun olmalı")
    public void responseBodyIçindeGelenBilgilerVerilenGunAralığınaUygunOlmalı() {

        List<String> dateTimes = response.jsonPath().getList("dateTime", String.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (String dt : dateTimes) {
            assertNotNull("dateTime alanı null geldi!", dt);

            LocalDate currentDate = LocalDate.parse(dt.substring(0, 10), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            assertTrue(
                    "Tarih beklenen aralıkta değil: " + currentDate,
                    !currentDate.isBefore(beginDate) && !currentDate.isAfter(endDate)
            );
        }
    }

    //============GetByContactMessageId REQUEST=====================

    @When("Contact message id olarak {int} bilgisi gonderilir")
    public void contactMessageIdOlarakBilgisiGonderilir(Integer id) {
        spec.pathParam("id", id);
    }

    @When("GET isteği gonderilir")
    public void getIsteğiGonderilir() {
        response = given(spec)
                .when()
                .get("/{first}/{second}/{id}");
        response.prettyPrint();
    }

    @Then("Donen mesaj bilgileri dogrulanmali")
    public void donenMesajBilgileriDogrulanmali() {
        JsonPath json = response.jsonPath();

        System.out.println("Response: " + response.asString());

        if (json.get("name") == null) {
            throw new AssertionError("API 'data' null döndü, muhtemelen token ya da endpoint hatalı!");
        }

        assertEquals("Sahin", json.getString("name"));
        assertEquals("sahin@batch.com", json.getString("email"));
        assertEquals("Uyarı", json.getString("subject"));
        assertEquals("A qui reiciendis eaque qui magni dolores eos aut nesciunt temporibus corporis doloremque omnis nihil neque illo.", json.getString("message"));
        assertEquals("20-10-2025 01:50", json.getString("dateTime"));
    }

    //============GET ALL REQUEST=====================

    @When("GET request gönderilir")
    public void getRequestGönderilir() {
        response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .get("/{first}/{second}");
    }

    @And("Response body boş olmamalı")
    public void responseBodyBoşOlmamalı() {

        String body = response.asString();
        assertTrue("Response body boş geldi!", body != null && body.length() > 10);
    }


    //============DeleteByContactMessageId REQUEST====================

    @And("Son olusturulan id ye ait bilgiler silinir")
    public void sonOlusturulanIdYeAitBilgilerSilinir() {

        Response getResponse = given(spec)
                .when()
                .get("/contactMessages/getAll");
        getResponse.then().statusCode(200);

        List<Map<String, Object>> messages = getResponse.jsonPath().getList("content");
        Map<String, Object> lastMessage = messages.get(messages.size() - 1);
        lastId = (Integer) lastMessage.get("id");


        spec.pathParams("first", "contactMessages", "second", "deleteById", "third", lastId);
        System.out.println("Silinecek son ID: " + lastId);
    }

    @When("DELETE isteği gönderilir")
    public void deleteIstegiGonder() {
        response = given(spec)
                .when()
                .delete("{first}/{second}/{third}");
        response.prettyPrint();
    }

    @And("DELETED Successfully mesajı doğrulanır")
    public void deletedSuccessfullyMesajiDogrulanir() {
        String actualMessage = response.jsonPath().getString("message");
        String expectedMessage = "Contact Message with id " + lastId + " is DELETED Successfully";
        assertEquals(expectedMessage, actualMessage);
    }

}
























































