package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import snackattack.pojos.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;
import static snackattack.stepdefs.Hook.spec;
import static snackattack.stepdefs.db_stepdefs.DB_PaymentManagementStepdefs.actualDatabase;
import static snackattack.utilities.TestData.userPaymentUserId;

public class API_PaymentControllerStepdefs {

    Response response;
    PaymentRefundableResponsePojo refundableResponse;
    private List<Map<String, Object>> payments = Collections.emptyList();
    JsonPath json;


    @When("Ödeme detaylari GET istegi ile alinir")
    public void ödemeDetaylariGETIstegiIleAlinir() {
        response = given(spec)
                .when()
                .get("{first}/{second}/{third}");
    }


    @And("Response body icinde ödeme bilgileri dogrulanmali")
    public void responseBodyIcindeÖdemeBilgileriDogrulanmali() {

        PaymentGetPaymentDataPojo dataPojo = new PaymentGetPaymentDataPojo(57, 65, "2025-10-24T13:08:42.586", 570.00, "CREDIT_CARD", "FAILED");
        PaymentGetPaymentResponsePojo expectedData = new PaymentGetPaymentResponsePojo(true, "Payment retrieved successfully", dataPojo, null, "OK", "2025-10-31T09:50:45.130495484");
        PaymentGetPaymentResponsePojo actualData = response.as(PaymentGetPaymentResponsePojo.class);
        assertEquals(expectedData.isSuccess(), actualData.isSuccess());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        assertEquals(expectedData.getData().getOrderId(), actualData.getData().getOrderId());
        assertEquals(expectedData.getData().getPaymentDate(), actualData.getData().getPaymentDate());
        assertEquals(expectedData.getData().getAmount(), actualData.getData().getAmount());
        assertEquals(expectedData.getData().getPaymentMethod(), actualData.getData().getPaymentMethod());
        assertEquals(expectedData.getData().getStatus(), actualData.getData().getStatus());
        assertEquals(expectedData.getErrors(), actualData.getErrors());
        assertEquals(expectedData.getStatus(), actualData.getStatus());

    }


    @Then("Status code dogrulanir")
    public void statusCodeDogrulanir() {
        response
                .then()
                .statusCode(200);
    }

    @When("Ödemeler GET istegi ile listelenir")
    public void ödemelerGETIstegiIleListelenir() {
        response = given(spec)
                .when()
                .get("{first}/{second}");

    }

    @And("Response body icinde ödeme listesi dogrulanmali")
    public void responseBodyIcindeÖdemeListesiDogrulanmali() {
        JsonPath jsonPath = response.jsonPath();

        // _embedded.paymentResponseDTOList dolu mu kontrol et
        assertTrue("Payment listesi bos olmamalı", jsonPath.getList("_embedded.paymentResponseDTOList").size() > 0);

    }


    @When("Failure reason bilgisini guncellemek icin PUT istegi gonderilir")
    public void failureReasonBilgisiniGuncellemekIcinPUTIstegiGonderilir() {
        String payload = "Kullanıcı iptal etti";
        response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}/{third}/{fourth}");


    }

    @And("Response body icinde failure reason bilgisi dogrulanmali")
    public void responseBodyIcindeFailureReasonBilgisiDogrulanmali() {
        PaymentFailureReasonPutDataPojo putDataPojo = new PaymentFailureReasonPutDataPojo(57, "2025-10-24T13:08:42.586", 570.00, "FAILED", "77adc922-ffce-4807-a904-44b6ad640287", "Kullanıcı iptal etti", true);
        PaymentFailureReasonPutResponsePojo expectedData = new PaymentFailureReasonPutResponsePojo(true, "Failure reason updated successfully", putDataPojo, null, "OK", "2025-10-31T15:29:00.240131137");

        PaymentFailureReasonPutResponsePojo actualData = response.as(PaymentFailureReasonPutResponsePojo.class);
        assertEquals(expectedData.isSuccess(), actualData.isSuccess());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        assertEquals(expectedData.getData().getPaymentDate(), actualData.getData().getPaymentDate());
        assertEquals(expectedData.getData().getAmount(), actualData.getData().getAmount());
        assertEquals(expectedData.getData().getStatus(), actualData.getData().getStatus());
        assertEquals(expectedData.getData().getTransactionReference(), actualData.getData().getTransactionReference());
        assertEquals(expectedData.getData().getFailureReason(), actualData.getData().getFailureReason());
        assertEquals(expectedData.getData().isIsRefundable(), actualData.getData().isIsRefundable());
        assertEquals(expectedData.getErrors(), actualData.getErrors());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
    }

    @When("Payment status bilgisini guncellemek icin PUT istegi gonderilir")
    public void paymentStatusBilgisiniGuncellemekIcinPUTIstegiGonderilir() {


        String payload = "{\n" +
                "  \"status\": \"FAILED\"\n" +
                "}";
        response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}/{third}/{fourth}");

    }

    @And("Response body icinde status bilgisi dogrulanmali")
    public void responseBodyIcindeStatusBilgisiDogrulanmali() {
        PaymentUpdatePaymentStatusPutDataPojo statusPutDataPojo = new PaymentUpdatePaymentStatusPutDataPojo(57, 65, 32, "2025-10-24T13:08:42.586", 570.00, "CREDIT_CARD", "77adc922-ffce-4807-a904-44b6ad640287", "Kullanıcı iptal etti", true, "USD", "PAYPAL");
        PaymentUpdatePaymentStatusPutResponsePojo expectedData = new PaymentUpdatePaymentStatusPutResponsePojo(true, "Payment status updated successfully: FAILED", statusPutDataPojo, null, "OK", "2025-10-31T15:42:13.076608024");

        PaymentUpdatePaymentStatusPutResponsePojo actualData = response.as(PaymentUpdatePaymentStatusPutResponsePojo.class);

        assertEquals(expectedData.isSuccess(), actualData.isSuccess());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        assertEquals(expectedData.getData().getOrderId(), actualData.getData().getOrderId());
        assertEquals(expectedData.getData().getUserId(), actualData.getData().getUserId());
        assertEquals(expectedData.getData().getPaymentDate(), actualData.getData().getPaymentDate());
        assertEquals(expectedData.getData().getAmount(), actualData.getData().getAmount());
        assertEquals(expectedData.getData().getPaymentMethod(), actualData.getData().getPaymentMethod());
        assertEquals(expectedData.getData().getTransactionReference(), actualData.getData().getTransactionReference());
        assertEquals(expectedData.getData().getFailureReason(), actualData.getData().getFailureReason());
        assertEquals(expectedData.getData().isIsRefundable(), actualData.getData().isIsRefundable());
        assertEquals(expectedData.getData().getCurrency(), actualData.getData().getCurrency());
        assertEquals(expectedData.getData().getPaymentGateway(), actualData.getData().getPaymentGateway());
        assertEquals(expectedData.getErrors(), actualData.getErrors());
        assertEquals(expectedData.getStatus(), actualData.getStatus());

    }

    @When("Yeni ödeme olusturmak icin POST istegi gonderilir")
    public void yeniÖdemeOlusturmakIcinPOSTIstegiGonderilir() {

        PaymentCreatePaymentPojo payload = new PaymentCreatePaymentPojo(80, 575757.00, "CREDIT_CARD", "2025-10-30T22:50:00.000Z", "USD", "STRIPE", "Test payment for order 77", "2025-10-30T22:50:00.000Z", "2025-10-30T22:50:00.000Z", null);
        response = given(spec)
                .body(payload)
                .when()
                .post("{first}/{second}/{third}");


    }

    @And("Response body icinde olusturulan ödeme bilgisi dogrulanmali")
    public void responseBodyIcindeOlusturulanÖdemeBilgisiDogrulanmali() {

        PaymentCreatePostDataPojo postDataPojo = new PaymentCreatePostDataPojo(72, 80, 167, "2025-10-30T22:50:00", 575757.00, "CREDIT_CARD", "5519b7f8-1db6-466f-b1c5-23b4f3f03893", "Ödeme başarısız", true, "USD", "STRIPE");
        PaymentCreatePostResponsePojo expectedData = new PaymentCreatePostResponsePojo(true, "Payment details retrieved successfully", postDataPojo, null, "OK", "2025-10-31T19:10:12.478739066");

        PaymentCreatePostResponsePojo actualData = response.as(PaymentCreatePostResponsePojo.class);

        assertEquals(expectedData.isSuccess(), actualData.isSuccess());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getOrderId(), actualData.getData().getOrderId());
        assertEquals(expectedData.getData().getPaymentGateway(), actualData.getData().getPaymentGateway());
        assertEquals(expectedData.getData().getAmount(), actualData.getData().getAmount());
        assertEquals(expectedData.getData().getFailureReason(), actualData.getData().getFailureReason());
        assertEquals(expectedData.getErrors(), actualData.getErrors());
        assertEquals(expectedData.getData().getOrderId(), actualData.getData().getOrderId());
        assertEquals(expectedData.getStatus(), actualData.getStatus());

    }


    @When("Transaction reference GET istegi ile alinir")
    public void transactionReferenceGETIstegiIleAlinir() {
        response = given(spec)
                .when()
                .get("{first}/{second}/{third}/{fourth}");

    }

    @And("Response body icinde transaction bilgisi dogrulanmali")
    public void responseBodyIcindeTransactionBilgisiDogrulanmali() {
        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("success", true);
        expectedData.put("message", "Transaction reference retrieved successfully");
        expectedData.put("data", "77adc922-ffce-4807-a904-44b6ad640287");
        expectedData.put("errors", null);
        expectedData.put("status", "OK");

        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("success"), actualData.get("success"));
        assertEquals(expectedData.get("message"), actualData.get("message"));
        assertEquals(expectedData.get("data"), actualData.get("data"));
        assertEquals(expectedData.get("errors"), actualData.get("errors"));
        assertEquals(expectedData.get("status"), actualData.get("status"));

    }


    @Then("Status code {int} oldugu dogrulanir")
    public void statusCodeOlduguDogrulanir(int statusCode) {

        int actualStatusCode = response.statusCode();

        if (actualStatusCode != statusCode) {
            System.out.println("Expected Status code: " + statusCode + " | Actual Status code: " + actualStatusCode);
            System.out.println("Response Body: " + response.getBody().asString());
        } else {
            System.out.println("Status code dogru: " + statusCode);
        }

        Assert.assertEquals("Status code eslesmiyor!", statusCode, actualStatusCode);
    }

    @When("Admin son ödeme bilgilerini almak icin GET istegi gonderir")
    public void adminSonÖdemeBilgileriniAlmakIcinGETIstegiGonderir() {
        response = given(spec)
                .when()
                .get("{first}/{second}/{third}");
    }

    @And("DB’deki son ödeme bilgileri API’den dönen bilgilerle tutarlı olmalı")
    public void dbDekiSonÖdemeBilgileriAPIDenDönenBilgilerleTutarlıOlmalı() {

        JsonPath json = response.jsonPath(); // response GET isteğinden gelmis olmalı
        String apiPaymentId = String.valueOf(json.getInt("data.id"));
        String apiOrderId = String.valueOf(json.getInt("data.orderId"));
        double apiAmount = json.getDouble("data.amount");
        String apiPaymentDate = json.getString("data.paymentDate"); // "2025-11-02T12:46:45.283"

// id ve order_id string olarak karsilastir
        assertEquals(apiPaymentId, String.valueOf(actualDatabase.get("id")));
        assertEquals(apiOrderId, String.valueOf(actualDatabase.get("order_id")));

// amount'u "$" ve 2 ondalık basamak ile karsilastir
        DecimalFormat df = new DecimalFormat("0.00");
        String actualAmountFormatted = "$" + df.format(actualDatabase.get("amount"));
        String apiAmountFormatted = "$" + df.format(apiAmount);
        assertEquals(apiAmountFormatted, actualAmountFormatted);

// payment_date karsilastirmasi
        String dbPaymentDateStr = (String) actualDatabase.get("payment_date_formatted"); // "02.11.2025 12:46:45"
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dbPaymentDate = LocalDateTime.parse(dbPaymentDateStr, dbFormatter);

// API tarihini LocalDateTime’a cevirip milisleri atıyoruz
        LocalDateTime apiPaymentDateTime = LocalDateTime.parse(apiPaymentDate.substring(0, 19));

        assertEquals(dbPaymentDate, apiPaymentDateTime);

    }

    // =========================================================
    //  GET /api/payments/{paymentId}/isRefundable
    // =========================================================



    @When("Refundable istegi GET istegi ile alinir")
    public void refundableIstegiGETIlegiIleAlinir() {

        response = given().spec(spec)
                .when()
                .get("{first}/{second}/{third}/{fourth}");


        refundableResponse = response.as(PaymentRefundableResponsePojo.class);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Body: " + refundableResponse);
    }

    @And("Response body icinde iade islemi bilgisi dogrulanmali")
    public void responseBodyIcindeIadeIslemiBilgisiDogrulanmali() {

        assertEquals(200, response.statusCode());
        assertNotNull("Response deserialize edilemedi", refundableResponse);

        assertTrue("Success bilgisi true olmali", refundableResponse.isSuccess());
        assertEquals("OK", refundableResponse.getStatus());
        assertTrue(refundableResponse.getMessage().toLowerCase().contains("refundable"));
        assertNotNull("Data (refundable true/false) null olamaz", refundableResponse.getData());

        System.out.println("✅ iade islemi bilgisi basariyla dogrulandi: " + refundableResponse.getData());
    }



    @And("Odeme listesi {string} olmali")
    public void odemeListesiOlmali(String listeDurumu) {
        boolean bosMu = payments == null || payments.isEmpty();
        switch (listeDurumu.toLowerCase()) {
            case "bos":
                assertTrue("Liste boş bekleniyordu ama dolu geldi!", bosMu);
                break;
            case "bos degil":
                assertFalse("Liste dolu bekleniyordu ama boş geldi!", bosMu);
                break;
            default:
                fail("listeDurumu yalnizca 'bos' veya 'bos degil' olabilir: " + listeDurumu);
        }
    }


    @And("Response body icinde basarili mesaj dogrulanmali")
    public void responseBodyIcindeBasariliMesajDogrulanmali() {

        String body = response.then().extract().asString();
        org.junit.Assert.assertNotNull("Response body null geldi!", body);
        org.junit.Assert.assertFalse("Response body bos geldi!", body.trim().isEmpty());


        io.restassured.path.json.JsonPath json = new io.restassured.path.json.JsonPath(body);


        org.junit.Assert.assertEquals(
                "User payments fetched successfully.",
                json.getString("message")
        );
    }



    @When("Odemeler GET istegi ile alinir")
    public void odemelerGETIstegiIleAlinir() {
        response = given(spec)
                .when()
                .get("{first}/{second}/{third}/{fourth}");

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.asString());

    }
    @When("Siparis ödemeleri GET istegi ile alinir")
    public void siparisOdemeleriGETIstegiIleAlinir() {
        // path: /api/payments/orders/{orderId}
        response = given(spec)
                .when()
                .get("{first}/{second}/{third}/{fourth}");
    }


    // LIST USER PAYMENTS –
    @When("Kullanici ödemeleri sayfalari  GET istegi ile alinir")
    public void kullaniciOdemeleriSayfaliGETIstegiyleAlinirr() {
        // page=0, size=10
        response = given(spec)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("{first}/{second}/{third}");
    }

    @And("Response body icinde {string} {string} olmali")
    public void responseBodyIcindeAlanDegeriOlmali(String key, String expectedValue) {

        // JSON Path ile key'e ait değeri al
        Object actualValue = response.jsonPath().get(key);

        // Beklenen değeri uygun tipe çevir (true/false/null/sayı vs)
        Object expected = coerceValue(expectedValue);

        // Hata mesajı ile doğrulama
        org.junit.Assert.assertEquals(
                "Response body içinde '" + key + "' alanı beklenen değerle eşleşmiyor.",
                expected, actualValue
        );
    }

    private Object coerceValue(String value) {
        if (value == null) return null;
        String trimmed = value.trim();

        if (trimmed.equalsIgnoreCase("null")) return null;
        if (trimmed.equalsIgnoreCase("true")) return true;
        if (trimmed.equalsIgnoreCase("false")) return false;

        try {
            if (trimmed.contains(".")) return Double.valueOf(trimmed);
            return Long.valueOf(trimmed);
        } catch (NumberFormatException ignored) {}

        return trimmed; // default olarak string döner
    }

}