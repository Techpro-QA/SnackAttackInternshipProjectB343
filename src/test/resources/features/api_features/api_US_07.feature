@AdditionCategoryController
Feature: Product API Testleri


  Background:
    Given API base url "http://207.154.209.12:8080"

  @AdditionCategoryPost
  @adminToken
  Scenario: Admin addition category olusturabilmeli
    When Yeni bir addition category icin payload hazırlanır
    And Kullanıcı addition category icin "POST" isteğini "/addition-category" endpointine gönderir
    Then Addition category icin Status kodu 200 olmalı
    And Addition Category'nin olusturuldugu kontrol edilir


  @AdditionCategoryGet
  @adminToken
  Scenario: Admin addition categoryleri gorebilmeli
    And Kullanıcı addition category icin "GET" isteğini "/addition-category" endpointine gönderir
    Then Addition category icin Status kodu 200 olmalı
    And Addition Category listesi dönmeli



  @AdditionCategoryPut
  @adminToken
  Scenario: Admin addition category olusturabilmeli
    When Yeni bir addition category icin put payload hazırlanır
    And Kullanıcı addition category icin "PUT" isteğini "/addition-category" endpointine gönderir
    Then Addition category icin Status kodu 200 olmalı
    And Addition Category'nin güncellendigi kontrol edilir


  @AdditionCategoryGet
  @adminToken
  Scenario: Admin addition categoryleri gorebilmeli
    And Kullanıcı addition category icin "GET" isteğini "/addition-category/admin" endpointine gönderir
    Then Addition category icin Status kodu 200 olmalı
    And Addition Category listesi dönmeli



  @AdditionCategoryDelete
  @adminToken
  Scenario: Admin addition category silebilmeli
    When Kullanıcı addition category icin "DYNAMIC DELETE" isteğini "/addition-category/" endpointine gönderir
    Then Addition category icin Status kodu 200 olmalı
    And Addition Category'nin api uzerinde silindigi kontrol edilir