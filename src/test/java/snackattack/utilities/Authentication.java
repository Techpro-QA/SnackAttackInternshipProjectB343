package snackattack.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

        // ✅ Cache alanları
        private static String adminToken;
    private static String userToken;
    private static String generateOrderAdminToken;

    // 🔹 ADMIN TOKEN
    public static String generateAdminToken() {
        if (adminToken != null) {
            System.out.println("♻️ Admin token cache’ten alındı.");
            return adminToken;
        }

        String url = ConfigReader.getProperty("snackUrlApi") + "/auth/login";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", ConfigReader.getProperty("adminEmail"));
        requestBody.put("password", ConfigReader.getProperty("adminPassword"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url);

        adminToken = response.jsonPath().getString("token");
        System.out.println("👑 Admin token oluşturuldu ve cache’lendi: " + adminToken);

        return adminToken;
    }

    // 🔹 USER TOKEN
    public static String generateUserToken() {
        if (userToken != null) {
            System.out.println("♻️ Kullanıcı token cache’ten alındı.");
            return userToken;
        }

        String url = ConfigReader.getProperty("snackUrlApi") + "/auth/login";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", ConfigReader.getProperty("user_email"));
        requestBody.put("password", ConfigReader.getProperty("user_password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url);

        userToken = response.jsonPath().getString("token");
        System.out.println("👤 Kullanıcı token oluşturuldu ve cache’lendi: " + userToken);

        return userToken;
    }

    // 🔹 DYNAMIC TOKEN (E2E testlerinde UI’dan olusan kullanici icin)
    private static String dynamicToken;
    public static String generateDynamicUserToken() {

        String url = ConfigReader.getProperty("snackUrlApi") + "/auth/login";

        if (TestData.email == null || TestData.password == null) {
            throw new IllegalStateException("⚠️ TestData içinde email/password bulunamadı! " +
                    "UI kaydı yapılmadan token alınamaz.");
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", TestData.email);
        requestBody.put("password", TestData.password);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url);

        response.then().statusCode(200);

         dynamicToken = response.jsonPath().getString("token");
        System.out.println("🚀 Dinamik kullanıcı token’ı oluşturuldu: " + dynamicToken);

        return dynamicToken;
    }
    // 🔹 PRIVATE ADMIN TOKEN (Grup testlerinden bağımsız özel admin hesabı için)
    private static String privateAdminToken;

    public static String generatePrivateAdminToken() {
        if (privateAdminToken != null) {
            System.out.println("♻️ Private admin token cache’ten alındı.");
            return privateAdminToken;
        }

        String url = ConfigReader.getProperty("snackUrlApi") + "/auth/login";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", ConfigReader.getProperty("privateAdminEmail"));
        requestBody.put("password", ConfigReader.getProperty("privateAdminPassword"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url);

        response.then().statusCode(200);

        privateAdminToken = response.jsonPath().getString("token");
        System.out.println("🔑 Private admin token oluşturuldu ve cache’lendi: " + privateAdminToken);

        return privateAdminToken;
    }

    // 🔹 PRIVATE USER TOKEN (grup testlerinden bağımsız özel kullanıcı için)
    private static String privateUserToken;
    public static String generatePrivateUserToken() {
        if (privateUserToken != null) {
            System.out.println("♻️ Private user token cache’ten alındı.");
            return privateUserToken;
        }

        String url = ConfigReader.getProperty("snackUrlApi") + "/auth/login";

        // Eğer TestData doluysa, register’dan gelen kullanıcıyla login ol
        String email = (TestData.email != null && !TestData.email.isEmpty())
                ? TestData.email
                : ConfigReader.getProperty("privateUserEmail");

        String password = (TestData.password != null && !TestData.password.isEmpty())
                ? TestData.password
                : ConfigReader.getProperty("privateUserPassword");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url);

        response.then().statusCode(200);

        privateUserToken = response.jsonPath().getString("token");
        System.out.println("👤 Private user token oluşturuldu ve cache’lendi: " + privateUserToken);

        return privateUserToken;
    }


    // 🧹 Cache temizleme metodu (isteğe bağlı)
    public static void clearTokens() {
        adminToken = null;
        userToken = null;
        privateUserToken = null;
        privateAdminToken = null;
        dynamicToken = null;
        System.out.println("🧹 Tüm token cache temizlendi.");
    }

    //  GENERATE ORDER ADMIN TOKEN
    public static String generateOrderAdminToken() {
        if (generateOrderAdminToken != null) {
            return generateOrderAdminToken;
        }
        String url = ConfigReader.getProperty("snackUrlApi") + "/auth/login";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", ConfigReader.getProperty("userOrderEmail"));
        requestBody.put("password", ConfigReader.getProperty("userOrderPassword"));
        Response response = given().contentType(ContentType.JSON).body(requestBody).when().post(url);
        generateOrderAdminToken = response.jsonPath().getString("token");
        return generateOrderAdminToken;
    }


}
