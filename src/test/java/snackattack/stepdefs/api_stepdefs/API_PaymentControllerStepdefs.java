package snackattack.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import snackattack.pojos.*;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static snackattack.stepdefs.Hook.spec;

public class API_PaymentControllerStepdefs {

    Response response;


    @When("Ödeme detaylari GET istegi ile alinir")
    public void ödemeDetaylariGETIstegiIleAlinir() {
        response = given(spec).when().get("{first}/{second}/{third}");
    }


    @And("Response body icinde ödeme bilgileri dogrulanmali")
    public void responseBodyIcindeÖdemeBilgileriDogrulanmali() {

        PaymentGetPaymentDataPojo dataPojo = new PaymentGetPaymentDataPojo(57,65,"2025-10-24T13:08:42.586",570.00,"CREDIT_CARD","FAILED");
        PaymentGetPaymentResponsePojo expectedData = new PaymentGetPaymentResponsePojo(true,"Payment retrieved successfully",dataPojo, null, "OK","2025-10-31T09:50:45.130495484");
        PaymentGetPaymentResponsePojo actualData = response.as(PaymentGetPaymentResponsePojo.class);
        assertEquals(expectedData.isSuccess(),actualData.isSuccess());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getOrderId(),actualData.getData().getOrderId());
        assertEquals(expectedData.getData().getPaymentDate(),actualData.getData().getPaymentDate());
        assertEquals(expectedData.getData().getAmount(),actualData.getData().getAmount());
        assertEquals(expectedData.getData().getPaymentMethod(),actualData.getData().getPaymentMethod());
        assertEquals(expectedData.getData().getStatus(),actualData.getData().getStatus());
        assertEquals(expectedData.getErrors(),actualData.getErrors());
        assertEquals(expectedData.getStatus(),actualData.getStatus());

    }


    @Then("Status code dogrulanir")
    public void statusCodeDogrulanir() {
        response.then().statusCode(200);
    }

    @When("Ödemeler GET istegi ile listelenir")
    public void ödemelerGETIstegiIleListelenir() {
        response = given(spec).when().get("{first}/{second}");

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
        response = given(spec).body(payload).when().put("{first}/{second}/{third}/{fourth}");


    }

    @And("Response body icinde failure reason bilgisi dogrulanmali")
    public void responseBodyIcindeFailureReasonBilgisiDogrulanmali() {
        PaymentFailureReasonPutDataPojo putDataPojo = new PaymentFailureReasonPutDataPojo(57,"2025-10-24T13:08:42.586",570.00,"FAILED","77adc922-ffce-4807-a904-44b6ad640287","Kullanıcı iptal etti",true);
        PaymentFailureReasonPutResponsePojo expectedData = new PaymentFailureReasonPutResponsePojo(true,"Failure reason updated successfully",putDataPojo,null,"OK","2025-10-31T15:29:00.240131137");

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
        response = given(spec).body(payload).when().put("{first}/{second}/{third}/{fourth}");

    }

    @And("Response body icinde status bilgisi dogrulanmali")
    public void responseBodyIcindeStatusBilgisiDogrulanmali() {
        PaymentUpdatePaymentStatusPutDataPojo statusPutDataPojo = new PaymentUpdatePaymentStatusPutDataPojo(57, 65,32,"2025-10-24T13:08:42.586",570.00,"CREDIT_CARD","77adc922-ffce-4807-a904-44b6ad640287", "Kullanıcı iptal etti",true, "USD","PAYPAL");
        PaymentUpdatePaymentStatusPutResponsePojo expectedData = new PaymentUpdatePaymentStatusPutResponsePojo(true,"Payment status updated successfully: FAILED",statusPutDataPojo, null, "OK","2025-10-31T15:42:13.076608024");

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
    }

    @And("Response body icinde olusturulan ödeme bilgisi dogrulanmali")
    public void responseBodyIcindeOlusturulanÖdemeBilgisiDogrulanmali() {
    }



    @When("Transaction reference GET istegi ile alinir")
    public void transactionReferenceGETIstegiIleAlinir() {
    }

    @And("Response body icinde transaction bilgisi dogrulanmali")
    public void responseBodyIcindeTransactionBilgisiDogrulanmali() {
    }

}
