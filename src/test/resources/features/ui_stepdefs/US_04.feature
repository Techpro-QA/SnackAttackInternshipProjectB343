@US_04
  Feature: Admin olarak sayfaya giris yapabilme
    Scenario: TC_01
      Given Sayfaya gidilir
      And Kullanici loginRegister'a tiklar
      And Kullanici "admin" olarak giriş yapar
      Then Kullanici basarili bir sekilde giris yaptigini dogrular
