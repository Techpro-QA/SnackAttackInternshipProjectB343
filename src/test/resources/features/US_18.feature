@US_18
  Feature: US_18 Payment Management Panel Testi

    @US_18_TC01
    Scenario: TC01 Admin olarak siteye giris yapilabilmeli

      Given Sayfaya gidilir
      And Kullanici loginRegister'a tiklar
      And Kullanici "admin" olarak giriş yapar
      And Admin Payment Management'a tiklar
      Then Payment ID, Order ID, User ID, Amount ve Payment Date sutunlarin altında veriler goruntulenir
      And Admin görüntülemek istedigi herhangi bir ödeme satirina tiklar ve Order Code, Address, Payment Method, Status ve Product bilgileri eksiksiz goruntulenir

