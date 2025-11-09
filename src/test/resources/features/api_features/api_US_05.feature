@Order_Controller
Feature: Order API Testleri

  Background:
    Given API base url "http://207.154.209.12:8080"

  @OrdersOrdersIdGet
  @adminToken
  Scenario:Admin OrdersId ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/12" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli

  @ListUserOrdersGet
  @adminToken
  Scenario:Admin OrdersId ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/listUserOrders?page=1&size=10&sort=orderDate&direction=DESC" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli


  @ListOrdersByIdGet
    @adminToken
    Scenario:Admin listOrdersById ile siparislerini gorebilmeli
      When Admin Order Controller icin "GET" isteğini "/api/orders/listOrdersById/119?page=1&size=10&sort=orderDate&direction=DESC" endpointine gönderir
      Then Order Controller Status kodu 200 olmalı
      And Order Controller listesi dönmeli

  @ListAllOrdersGet
  @adminToken
  Scenario:Admin listAllOrders ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/listAllOrders?page=1&size=10&sort=orderDate&direction=DESC" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli

  @Date-RangeGet
  @adminToken
  Scenario:Admin listAllOrders ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/date-range?startDate=2025-11-04&endDate=2025-11-05" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli

  @by-seller-sellerIdGet
  @adminToken
  Scenario:Admin listAllOrders ile siparislerini gorebilmeli
    When Admin Order Controller icin "GET" isteğini "/api/orders/by-seller/5" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order Controller listesi dönmeli

  @OrdersDelete
  @adminToken
  Scenario:Admin OrdersId ile siparislerini gorebilmeli
    When Admin Order Controller icin "DELETE" isteğini "/api/orders/108" endpointine gönderir
    Then Order Controller Status kodu 200 olmalı
    And Order'in silindigi kontrol edilir