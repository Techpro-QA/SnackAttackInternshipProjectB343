Feature: Admin herhangi bir üründe güncelleme yapabilmeli

  @US15e2e
  Scenario: TC_01-Admin herhangi bir üründe güncelleme yapabilmeli
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Kullanici 2 saniye bekler
    And Admin Product Management'a tiklar
    And Admin güncelleyeceği urunune tiklar
    And Admin "C:\Users\Mustafa\Downloads\pizzaimg.jpg" resim ekler
    And Admin Ürün Adı Textbox'ini "Havai Pizza" ile doldurur
    And Admin Açıklama Textbox'ini "Ananaslı pizza" ile doldurur
    And Admin İçerik Textbox'ini "sos,mozarella,ananas" ile doldurur
    And Admin Fiyat Textbox'ini "55" ile doldurur
    And Admin İndirim Textbox'ini "5" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "PIZZA" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin populer mi? ve mevcut mu? checkboxlarini secer
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellendigi kontrol edilir
    And Sayfa kapatilir


  @US15_E2E_API
  @noToken
  Scenario: Api ürün güncelleme kontrol
    When Kullanıcı "GET DYNAMIC" isteğini "/products/byId/" endpointine gönderir
    Then Status kodu 200 olmalı
    And Update edilen urun api'da kontrol edilir

   @US15_E2E_DB
  Scenario: Database ürün güncelleme kontrol
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.product where id=" sorgusu gonderilir
    Then Urunun guncellendigi database'de kontrol edilir
    And Baglanti kapatilir


