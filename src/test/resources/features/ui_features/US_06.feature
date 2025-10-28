@US_06
Feature: User ürünleri görebilmeli

  Background:
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici "user" olarak giriş yapar
    And Kullanici logoya tiklar
    And Kullanici Products'a tiklar


  Scenario: TC_01 Product sayfası başarıyla yüklenmeli ve 1 ürün görünmelidir.
    And İlk ürün kartında ürün adı alanı dogrulanir.
    And Sayfa kapatilir

  Scenario: TC_02 “Sonraki” sayfa düğmesi ile son sayfaya kadar gidilir ve ürünler listelenir.
    And Sonraki butonuna tıklanır.
    And Son sayfaya kadar sonraki butonuna tıklanır
    Then URL ve aktif sayfa göstergesi dogrulanir.
    And Sayfa kapatilir

  Scenario: TC_03 Ürün adının kısmi eşleşmesi ile arama sonuç verir.
    And Kullanici Arama alanina "soda" yazar.
    And Kullanici aramayı tetikler.
    And Sonuçlardaki ürün adlarında "soda" kontrol edilir.




