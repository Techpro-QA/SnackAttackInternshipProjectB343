@US_07
Feature: Ürün seçme ve sepete ekleme Testi
  Background:
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar


  @positive
  Scenario: TC_01 -Kullanici ürün seçebilmeli ve sepete ekleyebilmeli
    When "Kullanici" login Email alanina "hiqatogycy@mailinator.com" yazar
    And "Kullanici" login Password alanina "123456789" yazar
    And "Kullanici" loginButton'a tiklar
    Then "Kullanici" Anasayfaya yonlendigi dogrulanir
    And Kullanici Products'a tiklar
    And Kullanici doner sekmesine tiklar
    And Kullanici tavuk dönere tiklar
    And Kullanici mayonez seçer
    And Kullanici ketcap seçer
    And Kulanici "+" sekmesine tiklar
    Then Urun miktarı artığı doğrulanır
    And Kulanici "-" sekmesine tiklar
    Then Urun miktarı azaldığı doğrulanır
    And Kullanici Add To Cart sekmesine tiklar
    Then Ürünlerin sepete eklendiği doğrulanır
    And Kullanici View Cart iconuna tiklar
    And Kullanici checkout butonuna tiklar
    Then Sepetteki ürünler doğrulanır
    And Kullanici Craete Order sekmesine tiklar
    And Odeme sayfasına yönlendirildiği doğrulanır