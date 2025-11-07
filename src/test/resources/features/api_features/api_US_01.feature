@Product_Controller
Feature: Product API Testleri

  #Şeyda
  Background:
    Given API base url "http://207.154.209.12:8080"

  @products
  @noToken
  Scenario: Tüm ürünleri listele
    When Kullanıcı "GET" isteğini "/products" endpointine gönderir
    Then Status kodu 200 olmalı
    And Ürün listesi dönmeli

  @postProducts
  @noToken
  Scenario: Yeni ürün oluştur
    Given Yeni bir ürün payload hazırlanır
    When Kullanıcı "POST" isteğini "/products" endpointine gönderir
    Then Status kodu 200 olmalı
    And Ürün başarıyla oluşturulmalı

  @getbyId
  @noToken
  Scenario: ID’ye göre ürün getir
    When Kullanıcı "GET" isteğini "/products/byId/1" endpointine gönderir
    Then Status kodu 200 olmalı
    And Ürün bilgisi dönmeli

  @getAllProductsAdmin
  @noToken
  Scenario: Admin filtreli ürün listesini getir
    Given Admin ürün listeleme için query parametreleri hazırlanır
    When Kullanıcı "GET" isteğini "/products/allProduct-admin?page=1&size=1&available=true&active=true&category=2&search=pizza" endpointine gönderir
    Then Status kodu 200 olmalı
    And Filtreli ürün listesi dönmeli


    #Mustafa
  @ProductPut
  @adminToken
  Scenario: Admin bir ürünü güncelleyebilmeli
    Given Admin güncellenecek ürün payload'u hazırlanır
    When Kullanıcı "PUT" isteğini "/products" endpointine gönderir
    Then Status kodu 200 olmalı
    And Ürünün güncellendigi dogrulanir

  @ProductPopulerListGet
  @noToken
  Scenario: Admin bir ürünü güncelleyebilmeli
   When Kullanıcı "GET" isteğini "/products/populer-list?page=1&size=1" endpointine gönderir
   Then Status kodu 200 olmalı
   And Popular urunlerin goruldugu kontrol edilir

  @AllProduct-ClientGet
  @noToken
  Scenario: Admin bir ürünü güncelleyebilmeli
    When Kullanıcı "GET" isteğini "/products/allProduct-client?page=1&size=1&category=1&search=1" endpointine gönderir
    And Status kodu 200 olmalı


  @ProductDelete
  @noToken
  Scenario: Admin bir ürünü güncelleyebilmeli
    When Kullanıcı "GET" isteğini "/products/15" endpointine gönderir
    Then Status kodu 200 olmalı









