@e2eRegisterLoginVerify
Feature: User registration and login end-to-end verification
  Scenario: Yeni kullanici UI uzerinden kayit olur, API ve DB uzerinden dogrulanir
    #(UI adimlari)
    # (API adimlari)
    # (DB adimlari)

  @e2e_ui
    @noToken
  Scenario: Kullanici ui uzerinden gecerli datalarla kayit olabilmelidir
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici registerButon'a tiklar
    And Kullanici First Name alanina gecerli data girer
    And Kullanici Last Name alanina gecerli data girer
    And Kullanici Email alanina "random" yazar
    And Kullanici User Name alanina gecerli data girer
    And Kullanici Password alanina "random" yazar
    And Kullanici Confirm Password alanina Password ile ayni degeri girer
    And Kullanici Address alanina gecerli data girer
    And Kullanici Country Code select alanindan "TR" secer
    And Kullanici Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Kullanici register butonuna tiklar
    Then Kullanici login sayfasina yonlenir ve URL "/login" olarak devam eder
    And Sayfa kapatilir


      @e2e_api
        @noToken
      Scenario: Kullanici kayit olunan bir hesabin login dogrulamasini api uzerinden yapabilmelidir
        Given "login" endpoint'ine baglanti kurulur
        When Token almak icin POST istegi gonderilir
        Then Status code 200 olmali
        And Response body icinde token bilgisi dogrulanmali

        @e2e_api
        @e2eToken_ui_us01
        Scenario: Kullanici kayit olunan bir hesabin user dogrulamasini api uzerinden yapabilmelidir
          Given "user" endpoint'ine baglanti kurulur
          When Kullanici bilgileri GET istegi ile alinir
          Then Status code 200 olmali
          And Response body icinde email bilgisi dogrulanmali

          @e2e_db
            @noToken
          Scenario: Kullanici kayit olunan bir hesabin dorulamasini db uzerinden yapabilmelidir
            Given Veritabanı bağlantısı kurulur
            When Kullanici TestData’daki email bilgisiyle veritabaninda kaydi sorgular
            Then Kullanicinin veritabanindaki bilgileri TestData’daki datalarla uyusmalidir



