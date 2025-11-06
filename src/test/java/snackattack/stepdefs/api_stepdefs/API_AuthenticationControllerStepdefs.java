package snackattack.stepdefs.api_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import snackattack.utilities.Authentication;
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
    //String newPassword;
    String adminToken;
    String privateAdminToken;
    String privateUserToken;
    String userToken;
    String dynamicToken;

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
        }else if (endpoint.equalsIgnoreCase("PaymentsPaymentId")) {
            spec.pathParams("first", "api", "second", "payments","third",57);}
        else if (endpoint.equalsIgnoreCase("PaymentsLastPaymentID")) {
            spec.pathParams("first", "api", "second", "payments","third",Integer.parseInt(TestData.expectedFirstRowPaymentId));
        }else if (endpoint.equalsIgnoreCase("PaymentsCreatePayment")) {
            spec.pathParams("first", "api", "second", "payments","third","createPayment");
        }else if (endpoint.equalsIgnoreCase("PaymentsTransactionReference")) {
            spec.pathParams("first", "api", "second", "payments","third",57,"fourth","transactionReference");
        }else if (endpoint.equalsIgnoreCase("PaymentsFailureReason")) {
            spec.pathParams("first", "api", "second", "payments","third",57,"fourth","failureReason");
        }else if (endpoint.equalsIgnoreCase("PaymentIsRefundable")) {
            spec.pathParams("first", "api", "second", "payments","third",1,"fourth","isRefundable");
        }else if (endpoint.equalsIgnoreCase("PaymentsUserById")) {
            spec.pathParams("first", "api", "second", "payments","third","users","fourth",TestData.userPaymentUserId);
        }else if (endpoint.equalsIgnoreCase("PaymentsByOrderId")) {
            spec.pathParams("first", "api", "second", "payments","third","orders");
        }else if (endpoint.equalsIgnoreCase("ListUserPayments")) {
            spec.pathParams("first", "api", "second", "payments","third","listUserPayments");
        }else if (endpoint.equalsIgnoreCase("Payments")) {
            spec.pathParams("first", "api", "second", "payments");
        }else if (endpoint.equalsIgnoreCase("PaymentsUpdateStatus")) {
             spec.pathParams("first", "api", "second", "payments", "third", "updatePaymentStatus", "fourth", 57);
        }

        else {
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


        // Eğer kullanıcı başarıyla oluşturulduysa bilgileri kalıcı hale getir
        if (response.statusCode() == 201) {
            ConfigUpdater.updateProperty("privateUserEmail", TestData.email);
            ConfigUpdater.updateProperty("privateUserPassword", TestData.password);
            System.out.println("💾 Yeni kullanıcı bilgileri config'e eklendi: "
                    + TestData.email + " / " + TestData.password);
        } else {
            System.out.println("❌ Register başarısız, config güncellenmedi.");
        }


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

        // Config dosyasındaki ortak user bilgilerini oku
        String email = ConfigReader.getProperty("user_email");
        String password = ConfigReader.getProperty("user_password");

        requestBody.put("email", email);
        requestBody.put("password", password);

        System.out.println("🔐 Token almak icin kullanilacak email: " + email);
        System.out.println("🔐 Token almak icin kullanilacak password: " + password);

        // POST isteği gönder
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("{first}/{second}");

        response.prettyPrint();

        // Token'i al ve değişkene ata
        token = response.jsonPath().getString("token");
        System.out.println("🎟️ Alinan Token: " + token);

    }


    @When("{string} bilgileri GET istegi ile alinir")
    public void bilgileriGETIstegiIleAlinir(String rol) {
        // GET isteğini gönder ve yanıtı al
        response = given(spec)
                .when()
                .get("/{first}/{second}");

        System.out.println(rol + " bilgileri GET isteği ile alındı.");
        response.prettyPrint();
    }


    @And("Response body icinde guncel bilgiler dogrulanmali")
    public void responseBodyIcindeGuncelBilgilerDogrulanmali() {
        String body = response.asString().trim();

        System.out.println("Response body: " + body);

        // JSON degilse duz metin olarak dogruluyoruz
        assertEquals("Password UPDATED successfully", body);

        System.out.println("Sifre basariyla guncellendi = " + TestData.newPassword);
        ConfigUpdater.updateProperty("privateAdminPassword", TestData.newPassword);
    }


    @When("Private admin token almak icin POST istegi gonderilir")
    public void privateAdminTokenAlmakIcinPOSTIstegiGonderilir() {
        // set the expected data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", ConfigReader.getProperty("privateAdminEmail"));
        requestBody.put("password", ConfigReader.getProperty("privateAdminPassword"));

        // send request get response
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("{first}/{second}");

        response.prettyPrint();

        // Token'i al ve değişkene ata
        privateAdminToken = response.jsonPath().getString("token");
        System.out.println("🔑 Private Admin Token: " + privateAdminToken);
    }

    @And("Response body icinde private admin token bilgisi dogrulanmali")
    public void responseBodyIcindePrivateAdminTokenBilgisiDogrulanmali() {
        assertNotNull("Token null geldi!", privateAdminToken);
        System.out.println("Token basariyla alindi ve dogrulandi");
    }

    @When("Private admin sifresini guncellemek icin PATCH istegi gonderilir")
    public void privateAdminSifresiniGuncellemekIcinPATCHIstegiGonderilir() {
        // Eski şifreyi config’den al (private admin hesabına ait)
        String oldPassword = ConfigReader.getProperty("privateAdminPassword");

        // Yeni şifreyi dinamik oluştur
        TestData.newPassword = faker.internet().password(9, 12, true, true);

        // Request body hazırla
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("oldPassword", oldPassword);
        requestBody.put("newPassword", TestData.newPassword);

        // İsteği gönder
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("{first}/{second}");

        response.prettyPrint();

        // Yeni şifreyi kaydet (TestData ve Config’e)
        TestData.password = TestData.newPassword;
        ConfigUpdater.updateProperty("privateAdminPassword", TestData.newPassword);

        System.out.println("🔐 Private admin şifresi başarıyla güncellendi: " + TestData.newPassword);
    }

    @When("Private user token almak icin Post istegi gonderilir")
    public void privateUserTokenAlmakIcinPostIstegiGonderilir() {
        Map<String, Object> requestBody = new HashMap<>();

        // Öncelik: TestData -> yoksa ConfigReader (privateUser)
        String email = (TestData.email != null && !TestData.email.isEmpty())
                ? TestData.email
                : ConfigReader.getProperty("privateUserEmail");

        String password = (TestData.password != null && !TestData.password.isEmpty())
                ? TestData.password
                : ConfigReader.getProperty("privateUserPassword");

        requestBody.put("email", email);
        requestBody.put("password", password);

        System.out.println("🔐 Private token almak icin kullanilacak email: " + email);
        System.out.println("🔐 Private token almak icin kullanilacak password: " + password);

        // POST isteği gönder
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("{first}/{second}");

        response.prettyPrint();

        // Token'i al ve değişkene ata
        privateUserToken = response.jsonPath().getString("token");
        System.out.println("👤 Private User Token: " + privateUserToken);
    }

    @And("Response body icinde private email bilgisi dogrulanmali")
    public void responseBodyIcindePrivateEmailBilgisiDogrulanmali() {
        // Response body'den actual email aliyoruz
        String actualEmail = response.jsonPath().getString("email");

        // private user icin
        String expectedEmail = ConfigReader.getProperty("privateUserEmail");

        // Eğer TestData doluysa (register sonrası E2E test)
        if (TestData.email != null && !TestData.email.isEmpty()) {
            expectedEmail = TestData.email;
        }

        System.out.println("📧 Private email doğrulaması yapılıyor...");
        System.out.println("Expected: " + expectedEmail);
        System.out.println("Actual  : " + actualEmail);

        // Karşılaştırma
        assertEquals("❌ Private kullanıcı email bilgisi uyuşmuyor!", expectedEmail, actualEmail);
        System.out.println("✅ Private kullanıcı email bilgisi başarıyla doğrulandı!");
    }

    @And("Response body icinde user token bilgileri dogrulanmali")
    public void responseBodyIcindeUserTokenBilgileriDogrulanmali() {
        String userToken = Authentication.generateUserToken();
        assertNotNull("❌ User token null geldi!", userToken);
        System.out.println("✅ User token başarıyla alındı: " + userToken);
    }

    @And("Response body icinde admin token bilgileri dogrulanmali")
    public void responseBodyIcindeAdminTokenBilgileriDogrulanmali() {
        String adminToken = Authentication.generateAdminToken();
        assertNotNull("❌ Admin token null geldi!", adminToken);
        System.out.println("✅ Admin token başarıyla alındı: " + adminToken);
    }

    @And("Response body icinde private admin bilgileri dogrulanmali")
    public void responseBodyIcindePrivateAdminBilgileriDogrulanmali() {
        String privateAdminToken = Authentication.generatePrivateAdminToken();
        assertNotNull("❌ Private admin token null geldi!", privateAdminToken);
        System.out.println("✅ Private admin token başarıyla alındı: " + privateAdminToken);
    }

    @And("Response body icinde private user bilgileri dogrulanmali")
    public void responseBodyIcindePrivateUserBilgileriDogrulanmali() {
        String privateUserToken = Authentication.generatePrivateUserToken();
        assertNotNull("❌ Private user token null geldi!", privateUserToken);
        System.out.println("✅ Private user token başarıyla alındı: " + privateUserToken);
    }

    @And("Response body icinde dynamic token bilgileri dogrulanmali")
    public void responseBodyIcindeDynamicTokenBilgileriDogrulanmali() {
        String dynamicToken = Authentication.generateDynamicUserToken();
        assertNotNull("❌ Dynamic token null geldi!", dynamicToken);
        System.out.println("✅ Dynamic (E2E) token başarıyla alındı: " + dynamicToken);
    }

    @And("Response body icinde user email bilgisi dogrulanmali")
    public void responseBodyIcindeUserEmailBilgisiDogrulanmali() {
        String actualEmail = response.jsonPath().getString("email");
        String expectedEmail = ConfigReader.getProperty("user_email");

        System.out.println("📧 Email dogrulamasi yapiliyor...");
        System.out.println("Expected (user_email): " + expectedEmail);
        System.out.println("Actual: " + actualEmail);

        assertEquals("❌ Kullanıcı email bilgisi uyuşmuyor!", expectedEmail, actualEmail);
        System.out.println("✅ Kullanıcı email bilgisi başarıyla doğrulandı!");
    }
}

