package snackattack.stepdefs.api_stepdefs;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

public class UserControllerBaseSteps {

    protected Response response;
    protected String token;
    protected JsonPath jsonPath;

}


