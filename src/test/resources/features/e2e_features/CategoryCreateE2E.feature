@US17_E2E
Feature: Admin Category Management uzerinde category olusturabilmeli
  Scenario: Admin gecerli verilerle yeni kategori ekleyebilmeli

    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Kullanici 2 saniye bekler
    And Admin Category Management'a tiklar
    When Admin Category Management Panelinde New butonuna tiklar
    And Admin Name textbox'ini "çorbalar" ile doldurur
    And Active checkbox'inin secili oldugu kontrol edilir
    And Admin save butonuna tiklar
    Then Kategorinin eklendigi kontrol edilir
    And Sayfa kapatilir


  @noToken
  Scenario: Admin bütün categoryleri api uzerinde gorebilmeli
    When Kullanici category icin "GET" isteğini "/category/admin" endpointine gönderir
    Then Category status kodu 200 olmalı
    And UI'da olusturulan Categorynin goruldugu kontrol edilir



  Scenario: product category tablosundaki isimlerin benzersizlik kontrolu
    Given Veritabanı bağlantısı kurulur
    When Olusturulan Category icin "select * from snack_attack_db.category_entity where name=" sorgusu yapilir
    Then Olusturulan categorynin database uzerinde gorunurlugu dogrulanir
    And Baglanti kapatilir
