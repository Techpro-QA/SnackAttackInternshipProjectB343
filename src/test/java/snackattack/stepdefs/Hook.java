package snackattack.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import snackattack.utilities.Authentication;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;

public class Hook {

    public static RequestSpecification spec;

    @Before("@apie2e")
    public void setUp() throws Exception {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + Authentication.generateToken())
                .build();
    }
    
}
