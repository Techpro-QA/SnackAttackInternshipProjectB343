@US_20
Feature: User sayfadan mesaj gonderebilmeli

  @US_20_TC01
  Scenario: TC_01-Tum alanlar dogru dolduruldugunda basarili gonderim yapabilmeli
    Given Sayfaya gidilir
    And  Kullanici Contact'a tiklar
    And  Acilan pencerede Name icin gecerli bir data girilir
    And  Email  icin gecerli bir data girilir
    And  Subject alani doldurulur
    And  Message alani en az 3 karakter ile doldurulur.
    And  Kullanıcı consent  kutucugunu  isaretler
    And  Send butonu tıklanir
    Then Kayit isleminin basarili bir sekilde gerceklestigi dogrulanir"

  @US_20_TC02
  Scenario: TC_02-Name alani bos birakildiginda mesaj gonderim islemi gerceklesmemeli ve “Lutfen bu alani doldurun” uyarisi goruntulenmeli
    Given Sayfaya gidilir
    And  Kullanici Contact'a tiklar
    And  Acilan pencerede Name alani bos birakilir
    And  Email  icin gecerli bir data girilir
    And  Subject alani doldurulur
    And  Message alani en az 3 karakter ile doldurulur.
    And  Kullanıcı consent  kutucugunu  isaretler
    And  Send butonu tıklanir
    Then Lutfen bu alani doldurun mesaji goruntulenmeli