@e2eCreateProductVerify
Feature: Admin can add a new product and end-to-end verification
  Scenario: Admin UI uzerinden yeni bir ürün ekler, olusturulan ürün API ve DB uzerinden dogrulanir
    #(UI adimlari)
    # (API adimlari)
    # (DB adimlari)

  @e2e_ui
    @noToken
  Scenario: Admin ui uzerinden tüm alanlari eksiksiz doldurarak yeni ürün ekleyebilmeli
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Product Management'a tiklar
    And Admin New butonuna tiklar
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "PIZZA" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin Available ve Active seceneklerini secer
    And Admin "C:\Users\seyda\Desktop\hamburgerimg.webp" resim ekler
    And Admin Create Product butonuna tiklar
    Then Urunun eklendigi dogrulanir
    And Sayfa kapatilir

  @e2e_api
  @noToken
  Scenario: Admin api uzerinden tüm ürünleri listeleyebilmeli
    Given API base url "http://207.154.209.12:8080"
    When Kullanıcı "GET" isteğini "/products" endpointine gönderir
    Then Status kodu 200 olmalı
    And Ürün listesi dönmeli

  @e2e_db
  @noToken
  Scenario: Admin ürün ekleme kategorilerinin isimlerini ve bilgilerini veritabanından dogrulayabilmeli
    Given Veritabanı bağlantısı kurulur
    When Urun ekleme kategorileri tablosu veritabaninda aranir
    Then Kategori bilgileri veritabaninda bulunmalidir
    And Baglanti kapatilir



