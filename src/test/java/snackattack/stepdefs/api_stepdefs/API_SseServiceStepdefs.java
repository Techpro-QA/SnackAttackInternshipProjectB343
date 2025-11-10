package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class API_SseServiceStepdefs {

    private Response response;
    private String token;

    @Given("geçerli bearer token'a sahibim")
    public void gecerli_bearer_tokena_sahibim() {
        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0djVAdGVzdHY1LmNvbSIsImlhdCI6MTc2MjQ2NDMyMywiZXhwIjoxNzcxMTA0MzIzfQ.Dzl8El1AOrWIRPLW_aNsrnZupj55AkCKCySZUDdCOQY73-kc3-krKpC2qNLWmlM3mXLMBr2pbRM-rgqgg73L5g";
    }

    @When("/sse-service/subscribe endpoint'ine GET isteği gönderirim")
    public void sse_service_subscribe_endpoint_ine_get_istegi_gonderirim() {
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "text/event-stream")
                .when()
                .get("http://207.154.209.12:8080/subscribe");
    }

    @Then("sunucu bağlantıyı açık tutmalı")
    public void sunucu_baglantiyi_acik_tutmali() {
        // SSE sürekli açık bir bağlantı olduğu için 200 veya 204 dönmesi yeterli
        response.then().statusCode(anyOf(equalTo(200), equalTo(204)));
    }

    @Then("herhangi bir 401 Unauthorized hatası dönmemelidir")
    public void herhangi_bir_unauthorized_hatasi_donmemelidir() {
        response.then().statusCode(not(401));
    }
}


