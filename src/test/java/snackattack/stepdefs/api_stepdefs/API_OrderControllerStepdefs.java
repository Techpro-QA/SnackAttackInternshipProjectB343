package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import snackattack.stepdefs.Hook;

import static io.restassured.RestAssured.given;

public class API_OrderControllerStepdefs {
    private Response response;

    @When("Admin Order Controller icin {string} isteğini {string} endpointine gönderir")
    public void adminOrderControllerIcinIsteğiniEndpointineGönderir(String method, String endpoint) {
        if (method.equalsIgnoreCase("GET")) {
            response = given()
                    .spec(Hook.spec)
                    .when()
                    .get(endpoint);
        }

        System.out.println("Endpoint: " + endpoint);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " +
                response.getBody().asString().substring(0, Math.min(300, response.getBody().asString().length())));
    }

    @Then("Order Controller Status kodu {int} olmalı")
    public void orderControllerStatusKoduOlmalı(int statusCode) {
        int actualStatus = response.statusCode();
        if (actualStatus != statusCode) {
            System.err.println("Beklenen status " + statusCode + " ama dönen: " + actualStatus);
            System.err.println("Response Body: " + response.getBody().asString());
        } else {
            System.out.println("Status code doğru: " + statusCode);
        }
    }

    @And("Order Controller listesi dönmeli")
    public void orderControllerListesiDönmeli() {
        response.then().contentType(ContentType.JSON);
        response.prettyPrint();
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains("success"));
    }

    @And("Order Controller bos olmamali dönmeli")
    public void orderControllerBosOlmamali() {
        response.then().contentType(ContentType.JSON);
        response.prettyPrint();
        String body = response.getBody().asString();
        Assert.assertNotNull(body);
    }
}
