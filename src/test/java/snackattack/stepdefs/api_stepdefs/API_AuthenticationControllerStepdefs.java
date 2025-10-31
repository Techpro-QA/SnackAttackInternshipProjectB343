package snackattack.stepdefs.api_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import snackattack.pojos.UserRegisterPojo;
import snackattack.pojos.UserRegisterResponsePojo;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.ConfigUpdater;
import snackattack.utilities.TestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static snackattack.stepdefs.Hook.spec;

public class API_AuthenticationControllerStepdefs {

    Response response;
    Faker faker = new Faker();
    String token;
    String newPassword;
    String adminToken;

    @Given("{string} endpoint'ine baglanti kurulur")
    public void endpointIneBaglantiKurulur(String endpoint) {
        //set the url
        if (endpoint.equalsIgnoreCase("registerAnonymous")) {
            spec.pathParams("first", "auth", "second", "registerAnonymous");
        } else if (endpoint.equalsIgnoreCase("login")) {
            spec.pathParams("first", "auth", "second", "login");
        } else if (endpoint.equalsIgnoreCase("user")) {
            spec.pathParams("first", "auth", "second", "user");
        }else if (endpoint.equalsIgnoreCase("updatePassword")) {
            spec.pathParams("first", "auth", "second", "updatePassword");
        }  else {
            throw new IllegalArgumentException("Geçersiz endpoint adı: " + endpoint);
        }

    }

    @When("Kullanici olusturmak icin POST istegi gonderilir")
    public void kullaniciOlusturmakIcinPOSTIstegiGonderilir() {
        //set the expected data
        //Eger testdata da datalar yoksa fakerdan veri uretiyoruz
        if (TestData.email == null || TestData.firstName == null) {
            TestData.firstName = faker.name().firstName();
            TestData.lastName = faker.name().lastName();
            TestData.email = faker.internet().emailAddress();
            TestData.userName = faker.name().username();
            TestData.password = faker.internet().password(8, 10);
            String[] validPrefixes = {"530","535", "540", "545"};
            String prefix = validPrefixes[faker.number().numberBetween(0, validPrefixes.length)];
            TestData.phoneNumber = prefix + faker.number().digits(7);
            TestData.address = faker.address().fullAddress();
            System.out.println("⚠️ TestData boştu, Faker verileri üretildi.");
        }
        //set the payload
        // POJO ile body
        UserRegisterPojo requestBody = new UserRegisterPojo(
                TestData.firstName,
                TestData.lastName,
                TestData.email,
                TestData.userName,
                TestData.password,
                TestData.phoneNumber,
                "TR",
                TestData.address,
                true
        );
        //send request get response
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("{first}/{second}");
        response.prettyPrint();

    }

    @Then("Status code {int} olmali")
    public void statusCodeOlmali(int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    @And("Response body icinde kullanici bilgileri dogrulanmali")
    public void responseBodyIcindeKullaniciBilgileriDogrulanmali() {
        UserRegisterPojo requestBody = new UserRegisterPojo( TestData.firstName,
                TestData.lastName,
                TestData.email,
                TestData.userName,
                TestData.password,
                TestData.phoneNumber,
                "TR",
                TestData.address,
                true);
        UserRegisterResponsePojo actualData = response.as(UserRegisterResponsePojo.class);

        //Assertions
        assertEquals(requestBody.getEmail(), actualData.getEmail());
        assertEquals(requestBody.getFirstName(), actualData.getFirstName());
        assertEquals(requestBody.getLastName(), actualData.getLastName());
        assertTrue(actualData.isSuccess());
        assertEquals("Registration Successfully DONE", actualData.getMessage());
        assertEquals("MEMBER", actualData.getUserRole().getRoleType());
        assertEquals("Customers", actualData.getUserRole().getRoleName());

    }


    @When("Token almak icin POST istegi gonderilir")
    public void tokenAlmakIcinPOSTIstegiGonderilir() {
        //set the expected data
        //  Dynamic Request Body hazirligi
        Map<String, Object> requestBody = new HashMap<>();

        // Eger TestData doluysa (E2E senaryosu) onu kullan
        String email = (TestData.email != null && !TestData.email.isEmpty())
                ? TestData.email
                : ConfigReader.getProperty("user_email");

        String password = (TestData.password != null && !TestData.password.isEmpty())
                ? TestData.password
                : ConfigReader.getProperty("user_password");

        requestBody.put("email", email);
        requestBody.put("password", password);

        System.out.println(" Token almak icin kullanilacak email: " + email);
        System.out.println(" Token almak icin kullanilacak password: " + password);

        // send request
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("{first}/{second}");

        response.prettyPrint();

        //  Token'i al ve degiskene ata
        token = response.jsonPath().getString("token");
        System.out.println(" Alinan Token: " + token);

    }

    @And("Response body icinde token bilgisi dogrulanmali")
    public void responseBodyIcindeTokenBilgisiDogrulanmali() {
        assertNotNull("Token null geldi!", token);
        System.out.println("Token basariyla alindi ve dogrulandi");
    }

    @When("Kullanici bilgileri GET istegi ile alinir")
    public void kullaniciBilgileriGETIstegiIleAlinir() {
        //send request get response
        response = given(spec)
                .when()
                .get("/{first}/{second}");

        response.prettyPrint();
    }

    @And("Response body icinde email bilgisi dogrulanmali")
    public void responseBodyIcindeEmailBilgisiDogrulanmali() {
        //Assertions //bu stepi 2 feature icin kullaniyorum biri configdeki veriler icin biri fakerdan urettigim veriler icin
        String actualEmail = response.jsonPath().getString("email");
        String expectedEmail = ConfigReader.getProperty("user_email");
        // Eğer TestData.email doluysa (E2E senaryosu) onu kullan
        if (TestData.email != null && !TestData.email.isEmpty()) {
            expectedEmail = TestData.email;
        }

        System.out.println("Email dogrulamasi yapiliyor...");
        System.out.println("Expected: " + expectedEmail);
        System.out.println("Actual  : " + actualEmail);

        assertEquals("Kullanıcı email bilgisi uyuşmuyor!", expectedEmail, actualEmail);
        System.out.println("Kullanıcı email bilgisi basariyla dogrulandi!");
    }

    @When("Sifre guncellemek icin PATCH istegi gonderilir")
    public void sifreGuncellemekIcinPATCHIstegiGonderilir() {
        //set the expected data
        //eski sifreyi config properties dosyasindan aliyoruz
        String oldPassword = ConfigReader.getProperty("user_password");
        // Yeni sifre dinamik olusturuluyor, fakerdan uretiyoruz dinamik olmasi icin
        newPassword = faker.internet().password(9, 12, true, true);
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("oldPassword",oldPassword);//eski sifremiz yani configde olan
        requestBody.put("newPassword",newPassword);
        //get response
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("{first}/{second}");

        response.prettyPrint();


    }

    @And("Response body icinde guncel bilgiler dogrulanmali")
    public void responseBodyIcindeGuncelBilgilerDogrulanmali() {
        System.out.println("Response body: " + response.asString());

        // Response'i Map'e donusturuyoruz
        Map<String, Object> actualData = response.as(HashMap.class);

        // Response'u yazdir
        System.out.println("Response map: " + actualData);

        // Beklenen sonucu test ediyoruz
        assertEquals("Password UPDATED successfully", actualData.get("message"));
        assertEquals(true, actualData.get("success")); // eger API bu alani donuyorsa
        System.out.println("Sifre basariyla guncellendi = " + newPassword);
        //Degisen sifreyi (yenisini) config dosyasina kaydediyoruz
        ConfigUpdater.updateProperty("user_password",newPassword);

    }


    @When("Admin token almak icin POST istegi gonderilir")
    public void adminTokenAlmakIcinPOSTIstegiGonderilir() {
        // set the expected data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", ConfigReader.getProperty("adminEmail"));
        requestBody.put("password", ConfigReader.getProperty("adminPassword"));


        // send request get response
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("{first}/{second}");

        response.prettyPrint();

        // Token'i al ve degiskene ata
        adminToken = response.jsonPath().getString("token");
        System.out.println("Admin Token: " + adminToken);
    }
}

