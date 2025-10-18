Feature: Admin ürünleri görebilmeli, listeleyebilmeli ve arayabilmeli (US_13)

  Background:
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar

  @TC_01
  Scenario: TC_01-Admin basarili sekilde giris yapabilmeli
    And Kullanici "admin" olarak giriş yapar
    When Admin Product Management'a tiklar
    Then Admin Admin Panel'e yönlenmis olmali

  @TC_02
  Scenario: TC_02-Admin Product Management sayfasina erisebilmeli
    And Kullanici "admin" olarak giriş yapar
    When Admin Product Management'a tiklar
    Then Ürün listesi görünmeli

  @TC_03
  Scenario: TC_03-Ürünler listelenebilmeli
    And Kullanici "admin" olarak giriş yapar
    When Admin Product Management'a tiklar
    Then Ürünler listesinde en az bir ürün görünmeli

  @TC_04
  Scenario: TC_04-Ürünlerde arama yapılabilmeli
    And Kullanici "admin" olarak giriş yapar
    When Admin Product Management'a tiklar
    And Arama kutusuna "Hamburger" girip search butonuna tiklar
    Then Arama kriterine uygun ürün aramasi yapilabilmeli

  @TC_05
  Scenario: TC_05-Hatali admin bilgisi ile Login yapilamamali
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    When Gecersiz email "wrong@gmail.com" ve password "wrongpass" ile giris yapar
    Then Login hatasi mesaji görünmeli

  @TC_06
  Scenario: TC_06-Gecersiz ürün adiyla arama sonucu bulunamaz
    And Kullanici "admin" olarak giriş yapar
    When Admin Product Management'a tiklar
    And Arama kutusuna "NonExist" girip search butonuna tiklar
    Then "Ürün bulunamadi" mesaji görüntülenmeli

  @TC_07
  Scenario: TC_07-Gecersiz karakterlerle arama yapilamamali
    And Kullanici "admin" olarak giriş yapar
    When Admin Product Management'a tiklar
    And Arama kutusuna "@@##" girip search butonuna tiklar
    Then "Ürün bulunamadi" mesaji görüntülenmeli



