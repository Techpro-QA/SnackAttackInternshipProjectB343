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
    And "kullanici" registerButon'a tiklar
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "kullanici" login sayfasina yonlenir ve URL "/login" olarak devam eder
    And Sayfa kapatilir


      @e2e_api
        @noToken
      Scenario: Kullanici kayit olunan bir hesabin login dogrulamasini api uzerinden yapabilmelidir
        Given "login" endpoint'ine baglanti kurulur
        When Private user token almak icin Post istegi gonderilir
        Then Status code 200 olmali
        And Response body icinde private user bilgileri dogrulanmali

        @e2e_api
        @privateUserToken
        Scenario: Kullanici kayit olunan bir hesabin user dogrulamasini api uzerinden yapabilmelidir
          Given "user" endpoint'ine baglanti kurulur
          When "kullanici" bilgileri GET istegi ile alinir
          Then Status code 200 olmali
          And Response body icinde private email bilgisi dogrulanmali

          @e2e_db
            @noToken
          Scenario: Kullanici kayit olunan bir hesabin dorulamasini db uzerinden yapabilmelidir
            Given Veritabanı bağlantısı kurulur
            When Kullanici TestData’daki email bilgisiyle veritabaninda kaydi sorgular
            Then Kullanicinin veritabanindaki bilgileri TestData’daki data ile uyusmalidir



