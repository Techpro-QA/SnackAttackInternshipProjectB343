Feature: Admin herhangi bir üründe güncelleme yapabilmeli
  @US_15
  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Product Management'a tiklar
    And Admin güncelleyeceği urunune tiklar

  @US_15_TC_01
  Scenario: TC_01-Admin herhangi bir üründe güncelleme yapabilmeli
    When Admin Urun Resmi ekler
    And Admin Ürün Adı Textbox'ini doldurur
    And Admin Açıklama Textbox'ini doldurur
    And Admin İçerik Textbox'ini doldurur
    And Admin Fiyat Textbox'ini doldurur
    And Admin İndirim Textbox'ini doldurur
    And Admin urunun kategorisini secer
    And Admin urunun Ek Kategorisini secer
    And Admin "populer mi?" ve "mevcut mu?" checkboxlarini secer
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellendigi kontrol edilir

    @US_15_TC_02
    Scenario: Admin herhangi bir urunde guncelleme yaparken tum textbox'lar ve checkbox'lar bos birakildiginda guncelleme yapilamamali
    When Admin Urun Resmi bos birakir
    And Admin Ürün Adı Textbox'ini bos birakir
    And Admin Açıklama Textbox'ini bos birakir
    And Admin İçerik Textbox'ini bos birakir
    And Admin Fiyat Textbox'ini bos birakir
    And Admin İndirim Textbox'ini bos birakir
    And Admin urunun kategorisini bos birakir
    And Admin urunun Ek Kategorisini  bos birakir
    And Admin "populer mi?" ve "mevcut mu?" checkboxlarini bos birakir
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellenmedigi kontrol edilir

    Scenario: Admin herhangi bir urunun fiyat bilgisini ve indirim bilgisini negatif veriler ile güncelleyememeli
    When Admin Urun Resmi ekler
    And Admin Ürün Adı Textbox'ini doldurur
    And Admin Açıklama Textbox'ini doldurur
    And Admin İçerik Textbox'ini doldurur
    And Admin Fiyat Textbox'ini doldurur
    And Admin İndirim Textbox'ini doldurur
    And Admin urunun kategorisini secer
    And Admin urunun Ek Kategorisini secer
    And Admin "populer mi?" ve "mevcut mu?" checkboxlarini secer
    And Admin Güncelle butonuna tiklar
    Then Urunun guncellendigi kontrol edilir



























