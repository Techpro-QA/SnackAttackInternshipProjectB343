@Product_Controller
Feature: Product API Testleri

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









