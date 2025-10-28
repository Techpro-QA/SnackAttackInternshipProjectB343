package snackattack.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

   // public static String generateToken() {

        /*//set the url
        String url = ConfigReader.getProperty("snackUrlApi");
        //set the payload
        String credentials = "";
        //send request get response
        Response response = given().body(credentials).contentType(ContentType.JSON).when().post(url);

        //get and return token
        return response.jsonPath().getString("token");*/
    //}
        // ✅ Cache alanları
        private static String adminToken;
    private static String userToken;

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

    // 🧹 Cache temizleme metodu (isteğe bağlı)
    public static void clearTokens() {
        adminToken = null;
        userToken = null;
        System.out.println("🧹 Token cache temizlendi.");
    }


}
