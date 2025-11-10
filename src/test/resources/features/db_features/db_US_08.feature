Feature: Image Entity tablosundaki verilerin doğrulanması

  Scenario: image_entity tablosu boş mu kontrol edilir
    Given Database'e baglan
    When "image_entity" tablosundaki tüm kayıtları al
    Then Tablo sonucunun boş olduğu doğrulanır
