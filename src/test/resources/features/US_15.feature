Feature: Admin herhangi bir üründe güncelleme yapabilmeli
  @US15
  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Product Management'a tiklar
    And Admin güncelleyeceği urunune tiklar

  @US15_TC_01
  Scenario: TC_01-Admin herhangi bir üründe güncelleme yapabilmeli
    When Admin guncellenecek "C:\Users\Mustafa\Downloads\pizzaimg.jpg" resmi ekler
    And Admin Ürün Adı Textbox'ini "PIZZA06" ile doldurur
    And Admin Açıklama Textbox'ini "Margarita" ile doldurur
    And Admin İçerik Textbox'ini "sos,mozarella" ile doldurur
    And Admin Fiyat Textbox'ini "35" ile doldurur
    And Admin İndirim Textbox'ini "0" ile doldurur
    And Admin "PIZZA" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin populer mi? ve mevcut mu? checkboxlarini secer
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellendigi kontrol edilir
    And Sayfa kapatilir

  @US15_TC02
  Scenario: Admin herhangi bir urunde guncelleme yaparken tum textbox'lar ve checkbox'lar bos birakildiginda guncelleme yapilamamali
    When Admin Ürün Adı Textbox'ini "" ile doldurur
    And Admin Açıklama Textbox'ini "" ile doldurur
    And Admin İçerik Textbox'ini "" ile doldurur
    And Admin Fiyat Textbox'ini "" ile doldurur
    And Admin İndirim Textbox'ini "" ile doldurur
    And Admin "" kategorisini secer
    And Admin "" Ek Kategorisini secer
    And Admin populer mi? ve mevcut mu? checkboxlarini secer
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellenmedigi kontrol edilir
    And Sayfa kapatilir

  @US15_TC03
  Scenario: Admin herhangi bir urunun fiyat bilgisini ve indirim bilgisini negatif veriler ile güncelleyememeli
    When Admin guncellenecek urun resmi ekler
    And Admin Ürün Adı Textbox'ini "PIZZA06" ile doldurur
    And Admin Açıklama Textbox'ini "Margarita" ile doldurur
    And Admin İçerik Textbox'ini "sos,mozarella" ile doldurur
    And Admin Fiyat Textbox'ini "-35" ile doldurur
    And Admin İndirim Textbox'ini "-10" ile doldurur
    And Admin "PIZZA" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin populer mi? ve mevcut mu? checkboxlarini secer
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellendigi kontrol edilir
    And Sayfa kapatilir



























