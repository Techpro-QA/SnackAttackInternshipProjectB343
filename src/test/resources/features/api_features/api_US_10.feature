@Auth_Controller
  Feature: Yeni kullanici kaydi olusturma ve kontrol

    @register
    @noToken
    Scenario: Yeni kullanicinin API uzerinden olusturulmasi ve dogrulanmasi
      Given "registerAnonymous" endpoint'ine baglanti kurulur
      When Kullanici olusturmak icin POST istegi gonderilir
      Then Status code 201 olmali
      And Response body icinde kullanici bilgileri dogrulanmali

    @authLogin
    @noToken
    Scenario:  Kullanici gecerli bilgilerle login olup token alabilmeli
      Given "login" endpoint'ine baglanti kurulur
      When Token almak icin POST istegi gonderilir
      Then Status code 200 olmali
      And Response body icinde token bilgisi dogrulanmali


    @getUser
    @userToken
    Scenario: Kullanici bilgilerini token ile alabilmeli
      Given "user" endpoint'ine baglanti kurulur
      When Kullanici bilgileri GET istegi ile alinir
      Then Status code 200 olmali
      And Response body icinde email bilgisi dogrulanmali

    @authLoginAdmin
    @adminToken
    Scenario:  Kullanici gecerli bilgilerle login olup token alabilmeli
      Given "login" endpoint'ine baglanti kurulur
      When Admin token almak icin POST istegi gonderilir
      Then Status code 200 olmali
      And Response body icinde token bilgisi dogrulanmali


    @patchPassword
      @adminToken
        Scenario: Kullanici password bilgilerini guncelleyebilmeli
        Given "updatePassword" endpoint'ine baglanti kurulur
        When Sifre guncellemek icin PATCH istegi gonderilir
        Then Status code 200 olmali
        And Response body icinde guncel bilgiler dogrulanmali







