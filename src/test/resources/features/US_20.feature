@US_20
Feature: User sayfadan mesaj gonderebilmeli

  @US_20_TC01
  Scenario: TC_01-Tum alanlar dogru dolduruldugunda basarili gonderim yapabilmeli
    Given Sayfaya gidilir
    And  Anasayfadaki Contact butonuna tiklanir.
    And  Acilan pencerede Name icin gecerli bir data girilir
    And  Email  icin gecerli bir data girilir
    And  Subject alani doldurulur
    And  Message alani en az 3 karakter ile doldurulur.
    And  "I consent to the processing of my personal data according to the rule" kutucugu isaretlenir.
    And  Send butonu tıklanir
    Then Kayit isleminin basarili bir sekilde gerceklestigi dogrulanir"