
@US_18
  Feature: US_18 Payment Management Panel Testi


    Background:
      Given Sayfaya gidilir
      And Kullanici loginRegister'a tiklar


    @US_18-TC_01
    Scenario: TC01 Admin olarak siteye giris yapilabilmeli
      And Kullanici "admin" olarak giriş yapar
      And Sayfa kapatilir

    @US_18-TC_02
    Scenario: TC02 Payment management panel görüntülenebilmeli
      Given Kullanici "admin" olarak giriş yapar
      And Admin Payment Management'a tiklar
      Then Payment Management görüntülenir
      And Sayfa kapatilir


    @US_18-TC_03
    Scenario: TC03 Tüm ödemeler listelenebilmeli
      Given Kullanici "admin" olarak giriş yapar
      And Admin Payment Management'a tiklar
      Then Payment ID, Order ID, User ID, Amount ve Payment Date sutunlarin altında veriler goruntulenir
      And Sayfa kapatilir


    @US_18-TC_04
    Scenario: TC04 Admin herhangi bir ödeme detayını görüntüleyebilmeli ve siparişteki Order Code, Address, Payment Method, Status ayrıntılarını görebilmeli
      Given Kullanici "admin" olarak giriş yapar
      And Admin Payment Management'a tiklar
      And Sayfanin en sonuna gidilir
      Then Payment ID, Order ID, User ID, Amount ve Payment Date sutunlarin altında veriler goruntulenir
      And Admin görüntülemek istedigi herhangi bir ödeme satirina tiklar ve Order Code, Address, Payment Method, Status ve Product bilgileri eksiksiz goruntulenir

          # varsa 2. ve 3. sayfalar kontrol edilir
      And Sayfanin en sonuna gidilir
      When Eger ikinci sayfa aktifse Admin ikinci sayfaya gider ve ödeme detaylarını görüntüler
      And Eger ücüncü sayfa aktifse Admin ücüncü sayfaya gider ve ödeme detaylarını görüntüler
      And Sayfa kapatilir

