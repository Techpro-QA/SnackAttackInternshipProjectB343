@US_02
Feature: US_02 - Login islemleri
  Kullanici kayitli bilgileriyle giris yapabilmeli ve hatali durumlarda uygun uyari mesaji gormelidir.

  Background:
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar


  @positive
  Scenario: TC_01 - Kayitli olan kullanici gecerli datalar ile giris yapabilmeli
    When "kullanici" login Email alanina "user_email" yazar
    And "kullanici" login Password alanina "user_password" yazar
    And "kullanici" loginButton'a tiklar
    Then "kullanici" Anasayfaya yonlendigi dogrulanir
    And Sayfa kapatilir

  @neg-empty-email
  Scenario: TC_02 - Kayitli kullanici bos email ile giris yapamamali
    When "kullanici" login Email alanini bos birakir
    And "kullanici" login Password alanina "user_password" yazar
    And "kullanici" loginButton'a tiklar
    Then "Required" mesaji "email" altinda gorunmeli
    And Sayfa kapatilir


  @neg-empty-password
  Scenario: TC_03 - Kayitli kullanici bos password ile giris yapamamali
    When "kullanici" login Email alanina "user_email" yazar
    And "kullanici" login Password alanini bos birakir
    And "kullanici" loginButton'a tiklar
    Then "Required" mesaji "password" altinda gorunmeli
    And Sayfa kapatilir

  @neg-both-empty
  Scenario: TC_04 - Kayitli kullanici bos email ve bos password ile giris yapamamali
    When "kullanici" login Email alanini bos birakir
    And "kullanici" login Password alanini bos birakir
    And "kullanici" loginButton'a tiklar
    Then "Required" mesaji "email" altinda gorunmeli
    Then "Required" mesaji "password" altinda gorunmeli
    And "kullanici" kayit sayfasinda kalir ve URL "/login" olarak devam eder
    And Sayfa kapatilir

  @neg-unregistered
  Scenario: TC_05 - Kayitli olmayan email ile giris yapamamali
    When "kullanici" login Email alanina "abc1234@gmail.com" yazar
    And "kullanici" login Password alanina "user_password" yazar
    And "kullanici" loginButton'a tiklar
    Then Sayfada hata mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/login" olarak devam eder
    And Sayfa kapatilir

  @neg-invalid-format
  Scenario: TC_06 - Gecersiz formatta email ile giris yapamamali
    When "kullanici" login Email alanina "abc123.gmail.com" yazar
    And "kullanici" login Password alanina "user_password" yazar
    And "kullanici" loginButton'a tiklar
    Then Uyari yada hata mesaji email altinda gorunmeli
    And "kullanici" kayit sayfasinda kalir ve URL "/login" olarak devam eder
    And Sayfa kapatilir

  @neg-wrong-pass
  Scenario: TC_07 - Dogru email, yanlis password ile giris yapamamali
    When "kullanici" login Email alanina "user_email" yazar
    And "kullanici" login Password alanina "1234567" yazar
    And "kullanici" loginButton'a tiklar
    Then "kullanici" sayfada "authjs.dev" mesaji gormeli
    And "kullanici" kayit sayfasinda kalir ve URL "/login" olarak devam eder
    And Sayfa kapatilir

  @neg-wrong-email
  Scenario: TC_08 - Yanlis email, dogru password ile giris yapamamali
    When "kullanici" login Email alanina "abc123@gmail.com" yazar
    And "kullanici" login Password alanina "user_password" yazar
    And "kullanici" loginButton'a tiklar
    Then "kullanici" sayfada "authjs.dev" mesaji gormeli
    And "kullanici" kayit sayfasinda kalir ve URL "/login" olarak devam eder
    And Sayfa kapatilir

  @pos-forgotpassword-navigation
  Scenario: Forgot Password linki kullaniciyi sifre sifirlama sayfasina yonlendirmelidir
    When "kullanici" Forgot Password linkine tiklar
    Then "kullanici" sifre sifirlama sayfasina yonlendirildigini dogrular





