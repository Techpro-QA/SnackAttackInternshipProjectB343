@Payment-sse-controller
Feature: Payment-sse API Testleri

  @adminToken
  Scenario: : Admin Payment-sse-controller bilgilerine erisebilmeli
    Given API base url "http://207.154.209.12:8080"
    When Admin Payment sse controller icin "GET" isteğini "/payment-sse/subscribe" endpointine gönderir
    Then Payment sse controller Status kodu 200 olmalı
    And Payment sse controller listesi dönmeli