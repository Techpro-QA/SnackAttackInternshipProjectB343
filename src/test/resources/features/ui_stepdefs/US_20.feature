@US_20
Feature: User sayfadan mesaj gonderebilmeli

  Background:

  Given Sayfaya gidilir
    And  Kullanici Contact'a tiklar

  @US_20_TC01
  Scenario: TC_01-Tum alanlar dogru dolduruldugunda basarili gonderim yapabilmeli

    And  Acilan pencerede Name icin gecerli bir data girilir
    And  Email  icin gecerli bir data girilir
    And  Subject alani doldurulur
    And  Message alani en az 3 karakter ile doldurulur.
    And  Kullanıcı consent  kutucugunu  isaretler
    And  Send butonu tıklanir
    Then Kayit isleminin basarili bir sekilde gerceklestigi dogrulanir"
    And Sayfa kapatilir

  @US_20_TC02
  Scenario: TC_02-Name alani bos birakildiginda mesaj gonderim islemi gerceklesmemeli ve “Lutfen bu alani doldurun” uyarisi goruntulenmeli

    And  Acilan pencerede Name alani bos birakilir
    And  Email  icin gecerli bir data girilir
    And  Subject alani doldurulur
    And  Message alani en az 3 karakter ile doldurulur.
    And  Kullanıcı consent  kutucugunu  isaretler
    And  Send butonu tıklanir
    Then "Lütfen bu alanı doldurun." goruntulenmeli
    And Sayfa kapatilir

  @US_20_TC03
  Scenario: TC_03-Email alanı boş bırakıldığında mesaj gönderim işlemi gerçekleşmemeli ve “Lütfen bu alanı doldurun” uyarısı görüntülenmelidir.

    And  Acilan pencerede Name icin gecerli bir data girilir
    And  Email  alani bos birakilir.
    And  Subject alani doldurulur
    And  Message alani en az 3 karakter ile doldurulur.
    And  Kullanıcı consent  kutucugunu  isaretler
    And  Send butonu tıklanir
    Then "Lütfen bu alanı doldurun." goruntulenmeli
    And Sayfa kapatilir

  @US_20_TC04
  Scenario: TC_04-Email alaninda @ işareti eksik yazıldıgında mesaj oluşturma islemi gerceklesmemeli ve “Lütfen e-posta adresine bir "@" işareti ekleyin. "sahinbatch.com" adresinde "@" eksik.” uyarısı görüntülenmelidir

    And Acilan pencerede Name icin gecerli bir data girilir
    And Email  alaninda @ isareti yazilmaz
    And Subject alani doldurulur
    And Message alani en az 3 karakter ile doldurulur.
    And Kullanıcı consent  kutucugunu  isaretler
    And Send butonu tıklanir
    Then "Lütfen e-posta adresine bir \"@\" işareti ekleyin. \"sahinbatch.com\" adresinde \"@\" eksik." goruntulenmeli
    And Sayfa kapatilir

  @US_20_TC05
  Scenario: TC_05-Subject alani bos birakildiginda mesaj gonderim islemi gerceklesmemeli ve “Lütfen bu alanı doldurun” uyarısı görüntülenmelidir. (Negative Scenario)

    And Acilan pencerede Name icin gecerli bir data girilir
    And Email  icin gecerli bir data girilir
    And Subject alanini bos birakir
    And Message alani en az 3 karakter ile doldurulur.
    And Kullanıcı consent  kutucugunu  isaretler
    And Send butonu tıklanir
    Then "Lütfen bu alanı doldurun." goruntulenmeli

  @US_20_TC06
  Scenario: TC_06-Mesaj alanı boş bırakıldığında mesaj gönderim işlemi gerçekleşmemeli ve “Lütfen bu alanı doldurun” uyarısı görüntülenmelidir. (Negative Scenario)

    And Acilan pencerede Name icin gecerli bir data girilir
    And Email  icin gecerli bir data girilir
    And Subject alani doldurulur
    And Message alanini bos birakir
    And Kullanıcı consent  kutucugunu  isaretler
    And Send butonu tıklanir
    Then "Lütfen bu alanı doldurun." goruntulenmeli


  @US_20_TC07
  Scenario: TC_07-"I consent to the processing of my personal data according to the rules"" kutucugu işaretlenmemeli mesaj gönderim işlemi gerçekleşmemeli ve ""İlerlemek istiyorsanız lütfen bu kutucuğu işaretleyin"" uyarısı görüntülenmeli

    And Acilan pencerede Name icin gecerli bir data girilir
    And Email  icin gecerli bir data girilir
    And Subject alani doldurulur
    And Message alani en az 3 karakter ile doldurulur.
    And Kullanıcı consent  kutucugunu  isaretlenmez
    And Send butonu tıklanir
    Then "İlerlemek istiyorsanız lütfen bu kutuyu işaretleyin." goruntulenmeli















