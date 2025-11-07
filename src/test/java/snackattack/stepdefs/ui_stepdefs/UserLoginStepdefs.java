package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.Given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import snackattack.utilities.Authentication;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.WaitUtils;

import static io.restassured.RestAssured.given;

public class UserLoginStepdefs {
    @Given("Kullanıcı login olmuş şekilde siteye gelir")
    public void kullaniciLoginOlmusSekildeSiteyeGelir() {
        // UI aç
        Driver.getDriver().get(ConfigReader.getProperty("snackUrl"));

        // 1) API login
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + ConfigReader.getProperty("user_email") +
                        "\", \"password\": \"" + ConfigReader.getProperty("user_password") + "\" }")
                .post(ConfigReader.getProperty("snackUrlApi") + "/authentication-controller/authenticateLoginUser");

        // 2) Token ve user bilgilerini al
        String token = response.jsonPath().getString("token");
        String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");
        String email = response.jsonPath().getString("email");
        String userName = response.jsonPath().getString("userName");
        String id = response.jsonPath().getString("id");

        // 3) localStorage'a ekle
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.localStorage.setItem('token', '" + token + "');");

        String userJson = "{"
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"email\":\"" + email + "\","
                + "\"userName\":\"" + userName + "\","
                + "\"id\":\"" + id + "\""
                + "}";

        js.executeScript("window.localStorage.setItem('user', arguments[0]);", userJson);

        // 4) UI login state yenile
        Driver.getDriver().navigate().refresh();
        WaitUtils.waitFor(2);

        // 5) Anasayfaya tekrar dön
        Driver.getDriver().get(ConfigReader.getProperty("snackUrl"));
        WaitUtils.waitFor(2);

        System.out.println("✅ Kullanıcı LOGIN olmuş şekilde UI açıldı");
    }
}
