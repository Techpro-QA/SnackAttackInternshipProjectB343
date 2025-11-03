@US10_TC01
  Feature: Admin Customer Management uzerinde islem yapabilmeli

    Background:
      Given Sayfaya gidilir
      When Kullanici loginRegister'a tiklar
      And Kullanici "admin" olarak giriş yapar
      And Kullanici 2 saniye bekler
      And Admin Customer Management'a tiklar
      Then Tum user listesinin goruntulendigini kontrol edilir


    @US10_TC02
    Scenario: Admin, bir kullanici hesabini silebilmelidir
      When Admin Customer Management user listesinden herhangi bir kaydin cop kutusu icon'ina tiklar
      And Admin Allert mesajininda tamam'a tiklar
      Then Silinmesi hedeflenilen userin listede olmadigi kontrol edilir
      And Sayfa kapatilir

    @US10_TC03
    Scenario: Admin, bir kullanici hesabini silebilmelidir
      When Admin Customer Management user listesinden herhangi bir kaydin cop kutusu icon'ina tiklar
      And Admin Allert mesajininda tamam'a tiklar
      Then Silinmesi hedeflenilen userin listede olmadigi kontrol edilir
      And Sayfa kapatilir


