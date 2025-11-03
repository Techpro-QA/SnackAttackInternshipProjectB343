@US_08 @userToken
Feature: User sepete ekli ürünü/ürünleri sipariş verebilmeli/iptal edebilmeli

  Background:
    Given Kullanıcı login olmuş şekilde siteye gelir
    And Kullanici 2 saniye bekler
    And Kullanici Products'a tiklar
    And Kullanici sol ustteki ilk urune tiklar
    And Kullanici Add to Cart'a tiklar


  @US_08_TC_01
  Scenario:User ürünü sepetinde görebilmeli ve silebilmeli 1. yol
    Given Sag ust kosedeki sepet ikonuna tiklanir
    Then Kullanicinin seçtigi urunun sepetindeki urun ile aynı urun oldugu dogrulanir
    And Sayfa kapatilir



  @US_08_TC_03
    Scenario:
    And Cart Management Paneldeki sag ust kosedeki sepet ikonuna tiklanir
    And Kullanici Clear Cart a tiklar
    And Kullanici acılan pop up ta Clear Cart a tiklar
    Then Sepetin bos oldugu dogrulanır
    And Sayfa kapatilir

  @US_08_TC_04_05
  Scenario: User sepette ürün adedini arttırarak ve azaltarak güncelleme yapabilmeli
    Given Sag ust kosedeki sepet ikonuna tiklanir
    And Kullanici artı sembolune tıklar
    Then Sepetteki urun miktarının arttıgı oldugu dogrulanır
    And Kullanici 2 saniye bekler
    And Kullanici eksi sembolune tiklar
    Then Sepetteki urun miktarının azaldıgı oldugu dogrulanır
    And Sayfa kapatilir













