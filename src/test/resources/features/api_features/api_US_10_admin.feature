@Auth_Controller_Admin
  Feature: Admin token alma ve update yapma

    @authLoginPrivateAdmin
    @noToken
    Scenario:  Kullanici gecerli bilgilerle login olup token alabilmeli
      Given "login" endpoint'ine baglanti kurulur
      When Private admin token almak icin POST istegi gonderilir
      Then Status code 200 olmali
      And Response body icinde private admin token bilgisi dogrulanmali



    @patchPassword
    @privateAdminToken
    Scenario: Kullanici password bilgilerini guncelleyebilmeli
      Given "updatePassword" endpoint'ine baglanti kurulur
      When Private admin sifresini guncellemek icin PATCH istegi gonderilir
      Then Status code 200 olmali
      And Response body icinde guncel bilgiler dogrulanmali