
@US17
Feature: Admin Category Management uzerinde islem yapabilmeli

  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Kullanici 2 saniye bekler
    And Admin Category Management'a tiklar


    @US17_TC01
    Scenario: Admin gecerli verilerle yeni kategori ekleyebilmeli

      When Admin Category Management Panelinde New butonuna tiklar
      And Admin Name textbox'ini "çorbalar" ile doldurur
      And Active checkbox'inin secili oldugu kontrol edilir
      And Admin save butonuna tiklar
      Then Kategorinin eklendigi kontrol edilir
      And Sayfa kapatilir


     @US17_TC02
    Scenario: Admin kategori textbox'ini bos birakarak kategori olusturamamali

      When Admin Category Management Panelinde New butonuna tiklar
      And Admin Name textbox'ini "" ile doldurur
      And Active checkbox'inin secili oldugu kontrol edilir
      And Admin save butonuna tiklar
      Then Kategorinin eklenmedigi kontrol edilir
       And Sayfa kapatilir

     @US17_TC03
    Scenario: Admin var olan bir isimle yeni bir kategori olusturamamali

      When Admin Category Management Panelinde New butonuna tiklar
      And Admin Name textbox'ini "DONER" ile doldurur
      And Active checkbox'inin secili oldugu kontrol edilir
      And Admin save butonuna tiklar
      Then Something went wrong. Please try again. mesajinin alindigi kontrol edilir
       And Sayfa kapatilir


  @US17_TC04
  Scenario: Admin kategori silebilmeli

    When Admin silinmesi hedeflenilen kategorinin action bolumundeki delete icon'ina tiklar
    And Admin Allert mesajininda tamam'a tiklar
    Then Kategorinin silindigi kontrol edilir


 @US17_TC05
  Scenario: Admin kategori silme islemini iptal edebilmeli

   When Admin silinmesi hedeflenilen kategorinin action bolumundeki delete icon'ina tiklar
   And Admin Allert mesajininda iptal'e tiklar
   Then Kategorinin silinmedigi kontrol edilir




@US17_TC05
  Scenario: Admin paneldeki kategorilerin aktiflik durumunu gorebilmeli

  Then Aktif sutunun goruntulendigi kontrol edilir














