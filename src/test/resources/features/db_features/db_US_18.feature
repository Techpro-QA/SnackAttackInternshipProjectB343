@DB_US_18_User_Verification
Feature: Users tablosundaki verilerin doğrulanması

  Background:
    Given Veritabanı bağlantısı kurulur


  Scenario: Users tablosundaki kolon isimleri dogrulanmali
    When Kullanici config dosyasindaki email adresine sahip kaydi sorgular
    Then Kullanicinin veritabanindaki bilgileri config dosyasindaki datalarla uyusmalidir
    And Baglanti kapatilir

    Scenario: Veritabaninda olmayan email adresi sorgulandiginda kayit bulunmamalidir
      When Kullanici kayitli olmayan "celine.dn@gmail.com" email adresine sahip kaydi sorgular
      Then Herhangi bir kayit donmemelidir
      And Baglanti kapatilir

      Scenario: Users tablosunda yinelenen (duplicate) email adresi olmamalidir
        When Kullanici users tablosundaki email adreslerini gruplayarak sorgular
        Then Her email adresi yalnizca bir kez bulunmalidir
        And Baglanti kapatilir
