package snackattack.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken() {

        //set the url
        String url = ConfigReader.getProperty("baseUrl")+"/login";
        //set the payload
        String credentials = "";
        //send request get response
        Response response = given().body(credentials).contentType(ContentType.JSON).when().post(url);

        //get and return token
        return response.jsonPath().getString("token");
    }


}
