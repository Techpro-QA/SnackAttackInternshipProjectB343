package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import snackattack.pojos.ContactMessagePostPojo;
import snackattack.stepdefs.Hook;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static snackattack.stepdefs.Hook.spec;

public class ApiContactMessageStepdefs {

    Response response;


    @And("ContactMessages save endpoint'ine baglanti kurulur")
    public void contactmessagesSaveEndpointIneBaglantiKurulur() {
        spec.pathParams("first","contactMessages","second","save");

    }

    @When("Yeni bir contact message aşağıdaki bilgilerle gönderilir:")
    public void yeniBirContactMessageAşağıdakiBilgilerleGönderilir() {

        ContactMessagePostPojo contactMessagePostPojo = new ContactMessagePostPojo("cigdem","amancigdem@gmail.com","Malıyet","abc");
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(contactMessagePostPojo)
                .when()
                .post("{first}/{second}");

        response.prettyPrint();

    }

    @And("Response içinde {string} alanının {string} içerdigi dogrulanır")
    public void responseIçindeAlanıIçermeli(String msj, String message) {

        response.then().body(msj, containsString(message));
    }


}
