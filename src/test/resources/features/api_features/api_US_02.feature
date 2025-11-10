@Category_Controller
Feature: Category API Testleri

  Background:
    Given API base url "http://207.154.209.12:8080"

    @CategoryPost
      @noToken
      Scenario: Admin api uzerinden category  olusturabilmeli
      When Olusturulacak catergory icin payload hazirlanir
      And Kullanici category icin "POST" isteğini "/category" endpointine gönderir
      Then Category status kodu 200 olmalı
      And Category'nin olusturuldugu control edilir

    @CategoryGET
    @noToken
    Scenario: Admin bütün categoryleri api uzerinde gorebilmeli
      When Kullanici category icin "GET" isteğini "/category" endpointine gönderir
      Then Category status kodu 200 olmalı
      And Category'lerin goruldugu kontrol edilir



      @CategoryPUT
      @noToken

      Scenario: Admin butun categoryleri api uzerinde guncelleyebilmeli
        When Guncellenecek urunun payloadu hazirlanir
        And Kullanici category icin "PUT" isteğini "/category" endpointine gönderir
        Then Category status kodu 200 olmalı
        And Categorynin guncellendigi kontrol edilir


  @CategoryAdminGET
  @noToken
  Scenario: Admin bütün categoryleri api uzerinde gorebilmeli
    When Kullanici category icin "GET" isteğini "/category/admin" endpointine gönderir
    Then Category status kodu 200 olmalı
    And Category'lerin goruldugu kontrol edilir



 @CategoryDelete
  @noToken
  Scenario: Admin bütün categoryleri api uzerinden silebilmeli
    When Kullanici category icin "DYNAMIC DELETE" isteğini "/category/" endpointine gönderir
    Then Category status kodu 200 olmalı
    And Categorynin silindigi kontrol edilir



