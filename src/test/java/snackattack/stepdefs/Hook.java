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

    // 🔸 Öncelikle her senaryodan önce cache temizliği
    @Before(order = 0)
    public void clearAllTokensBeforeScenario() {
        Authentication.clearTokens();
        System.out.println("🧹 [HOOK] Tüm token cache temizlendi (her senaryo öncesi).");
    }

    // 🔸 Admin token gerektiren testler
    @Before("@adminToken")
    public void setUpAdminToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .addHeader("Authorization", "Bearer " + Authentication.generateAdminToken())
                .setContentType(ContentType.JSON)
                .build();

        System.out.println("✅ Admin token ile test başlatıldı.");
    }
    // 🔸 Private admin token gerektiren testler
    @Before("@privateAdminToken")
    public void setUpPrivateAdminToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .addHeader("Authorization", "Bearer " + Authentication.generatePrivateAdminToken())
                .setContentType(ContentType.JSON)
                .build();

        System.out.println("✅ Private admin token ile test başlatıldı.");
    }

    // 🔸 User token gerektiren testler
    @Before("@userToken")
    public void setUpUserToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .addHeader("Authorization", "Bearer " + Authentication.generateUserToken())
                .setContentType(ContentType.JSON)
                .build();

        System.out.println("✅ Kullanıcı token ile test başlatıldı.");
    }
    // 🔸 Private user token gerektiren testler
    @Before("@privateUserToken")
    public void setUpPrivateUserToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .addHeader("Authorization", "Bearer " + Authentication.generatePrivateUserToken())
                .setContentType(ContentType.JSON)
                .build();

        System.out.println("✅ Private user token ile test başlatıldı.");
    }


    // 🔸 E2E senaryolarda (UI'dan olusan kullanici ile) token alma
    @Before("@dynamicToken")
    public void setUpDynamicToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .addHeader("Authorization", "Bearer " + Authentication.generateDynamicUserToken())
                .setContentType(ContentType.JSON)
                .build();

        System.out.println("✅ Dinamik (E2E) kullanici token ile test başlatıldı.");
    }


    // 🔸 Token’sız endpointler
    @Before("@noToken")
    public void setUpNoToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .setContentType(ContentType.JSON)
                .build();

        System.out.println("✅ Token’sız test başlatıldı.");
    }

    // Order id icin olusturuldu
    @Before("@userOrderToken")
    public void setUpUserOrderToken() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("snackUrlApi"))
                .addHeader("Authorization", "Bearer " + Authentication.generateOrderAdminToken())
                .setContentType(ContentType.JSON)
                .build();

    }

    @After("@closePage")
    public void tearDownForPaymentScenario() {
        Driver.closeDriver();
    }

}