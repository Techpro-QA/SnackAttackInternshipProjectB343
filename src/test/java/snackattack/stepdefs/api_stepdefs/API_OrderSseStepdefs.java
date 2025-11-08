package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class API_OrderSseStepdefs {

    private final String baseUrl = "http://207.154.209.12:8080";

    @And("Kullanıcı SSE {string} endpointine bağlanır ve eventleri dinler")
    public void kullanıcı_sse_endpointine_bağlanır_ve_eventleri_dinler(String endpoint) {
        Response response = RestAssured
                .given()
                .header("Accept", "text/event-stream")
                .get(baseUrl + "/" + endpoint + "?timeout=0");

        String body = response.getBody().asString();
        boolean eventAlindi = body.contains("data:");

        if (eventAlindi) {
            System.out.println("🎯 Event alındı: " + body);
        } else {
            System.out.println("⚠ 10 saniye içinde SSE event gelmedi.");
        }
    }
}
