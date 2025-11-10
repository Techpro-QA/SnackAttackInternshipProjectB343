package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import snackattack.pojos.EmailRequestPojo;
import snackattack.pojos.EmailResponsePojo;
import snackattack.pojos.UserPojo;
import snackattack.pojos.UserRolePojo;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class API_EmailControllerStepdefs {

    private RequestSpecification spec;
    private Response response;
    private EmailResponsePojo actualData;

    @Given("{string} endpointine baglanti kurulur")
    public void endpointIneBaglantiKurulur(String endpointKey) {
        assertEquals("EmailSend", endpointKey);

        spec = given()
                .baseUri("http://207.154.209.12:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParams("first", "email",
                        "second", "send");
    }

    @When("Admin geçerli kullanıcıya email gönderir ve response alınır")
    public void adminGecerliKullaniciyaEmailGonderirVeResponseAlinir() {
        UserRolePojo role = new UserRolePojo(2, "MEMBER", "Member");

        UserPojo user = new UserPojo(
                "techproakif",
                "akif@gmail.com",
                "Akif",
                "Tech",
                "Sancak Mahallesi Beylerbeyi Sokak Aşağı Yukarı Caddesi",
                "5554443333",
                "TR",
                role,
                "Dummy123!",
                true
        );

        EmailRequestPojo expectedBody = new EmailRequestPojo(
                "adminnew@gmail.com",       // gönderici / hedef şema backend’e göre
                "Sistem Bildirimi",         // <-- BOS KALMAMALI
                Collections.singletonList(user),
                Collections.emptyList()
        );

        response = given(spec)
                .body(expectedBody)
                .when()
                .post("{first}/{second}")
                .then()
                .extract()
                .response();

        // 200 ise POJO'ya parse et
        if (response.statusCode() == 200) {
            actualData = response.as(EmailResponsePojo.class);
        }
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Raw Body: " + response.asString());
        System.out.println("Parsed: " + actualData);
    }

    @Then("Status codenun {int} oldugu dogrulanir")
    public void statusCodeOlduguDogrulanir(Integer expected) {
        assertNotNull("Response null geldi!", response);
        assertEquals("Status code beklenenle eşleşmiyor!", expected.intValue(), response.statusCode());
    }

    @And("Response body icinde basarili email gönderimi mesaji dogrulanmali")
    public void responseBodyIcindeBasariliEmailGonderimiMesajiDogrulanmali() {
        assertNotNull("POJO parse edilemedi!", actualData);
        assertTrue("success false!", actualData.isSuccess());
        assertTrue("data false!", actualData.isData());
        assertEquals("OK", actualData.getStatus());
        assertNull("errors null olmalı!", actualData.getErrors());
        assertNotNull("message null!", actualData.getMessage());

        // Ör. "EMAIL Was sent SUCCESSFULLY toadminnew@gmail.com"
        String msg = actualData.getMessage();
        assertTrue("Mesaj 'EMAIL Was sent SUCCESSFULLY' içermiyor! -> " + msg,
                msg.contains("EMAIL Was sent SUCCESSFULLY"));
        // timestamp kabaca ISO-8601 kontrolü
        assertNotNull(actualData.getTimestamp());
        assertTrue(actualData.getTimestamp().contains("T"));
    }
}
