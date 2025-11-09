@Order_Controller
Feature: Order API Testleri

  Background:
    Given API base url "http://207.154.209.12:8080"

    @adminToken
    Scenario:Admin listOrdersById ile siparislerini gorebilmeli
      When Admin Order Controller icin "GET" isteğini "/api/orders/listOrdersById/119?page=1&size=10&sort=orderDate&direction=DESC" endpointine gönderir
      Then Order Controller Status kodu 200 olmalı
      And Order Controller listesi dönmeli

  @adminToken
  Scenario:Admin listAllOrders ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/listAllOrders?page=1&size=10&sort=orderDate&direction=DESC" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli

  @adminToken
  Scenario:Admin listAllOrders ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/date-range?startDate=2025-11-04&endDate=2025-11-05" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli

  @adminToken
  Scenario:Admin listAllOrders ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/by-seller/5" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli