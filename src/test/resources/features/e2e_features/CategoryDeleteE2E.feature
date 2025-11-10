
@US17_E2E_Delete
Feature: Admin Category Management uzerinde category silebilmeli


Scenario: Admin kategori silebilmeli
  Given Sayfaya gidilir
  When Kullanici loginRegister'a tiklar
  And Kullanici "admin" olarak giriş yapar
  And Kullanici 2 saniye bekler
  And Admin Category Management'a tiklar
  When Admin silinmesi hedeflenilen kategorinin action bolumundeki delete icon'ina tiklar
  And Admin Allert mesajininda tamam'a tiklar
  Then Kategorinin silindigi kontrol edilir

  @noToken
  Scenario: Admin bütün categoryleri api uzerinde gorebilmeli
    When Kullanici category icin "GET" isteğini "/category/admin" endpointine gönderir
    Then Category status kodu 404 olmalı
    And Categorynin silindigi kontrol edilir

  Scenario: product category tablosundaki isimlerin benzersizlik kontrolu
    Given Veritabanı bağlantısı kurulur
    When Olusturulan Category icin "select * from snack_attack_db.category_entity where name=" sorgusu yapilir
    Then Olusturulan categorynin database uzerinde silindigi dogrulanir
    And Baglanti kapatilir