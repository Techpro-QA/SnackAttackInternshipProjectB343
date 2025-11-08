@US16_E2E
Feature: Admin herhangi bir ürünü silebilmeli
  Scenario: Admin herhangi bir ürünü silebilmeli
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Product Management'a tiklar
    And Admin silmek istedigi urunun Actions kismindaki çop kutusu(delete) icon'una tiklar
    And Admin Are you sure you want to delete this product? alertini görür ve Tamam'a tiklar
    And Admin mevcut mu? checkbox'indaki tiki kaldirir(unsellected)
    And Admin Güncelle butonuna tiklar
    Then Urunun silindigi kontrol edilir
    And Sayfa kapatilir

  @noToken
  Scenario: Api ürün silme kontrol
    When Kullanıcı "GET DYNAMIC" isteğini "/products/" endpointine gönderir
    Then Status kodu 404 olmalı
    And Urunun api uzerinde silindigi kontrol edilir

  Scenario: Database ürün silme kontrol
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.product where id=" sorgusu gonderilir
    Then Urunun silindigi database uzerinden kontrol edilir
    And Baglanti kapatilir