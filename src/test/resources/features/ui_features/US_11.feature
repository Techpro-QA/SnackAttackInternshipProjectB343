@US11
  Feature: Admin Admin Management uzerinde islem yapabilmeli

    Background:
      Given Sayfaya gidilir
      When Kullanici loginRegister'a tiklar
      And Kullanici "admin" olarak giriş yapar
      And Kullanici 2 saniye bekler
      And Admin Admin Management'a tiklar
      Then Tum admin listesinin goruntulendigini kontrol edilir


      #TC01 VE TC02 otomasyon testi background kapsamında otomatik görüldüğü için es geçilmiştir




    @US11_TC03
    Scenario: Admin, bir sistem hesabini (admin@example.com haric) silebilmelidir
      When Admin Admin Management listesinden herhangi bir kaydin cop kutusu icon'ina tiklar
      And Admin Allert mesajininda tamam'a tiklar
      Then Silinmesi hedeflenilen adminin listede olmadigi kontrol edilir
      And Sayfa kapatilir



    @US11_TC04
    Scenario: Admin, ozel sistem hesabini (admin@example.com) silememelidir
      When Sayfanin en sonuna gidilir
      And Admin ikinci sayfa butonuna tiklar
      And Admin Management sayfasida admin@example.com satirinda ki cop kutusu icon'ina tiklar
      And Admin Allert mesajininda tamam'a tiklar
      Then admin@example.com kaydinin listesinde kaldigi ve silinmedigi kontrol edilir
      And Sayfa kapatilir
