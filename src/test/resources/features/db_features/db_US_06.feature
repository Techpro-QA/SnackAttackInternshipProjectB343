@DB_US_O6
Feature: Kategori isimlerinin ve bilgilerinin dogrulanması

  Scenario: Kategori isimlerinin boş olmadığını kontrol et
    Given Database'e baglan
    When "category_entity" tablosundaki tüm kayıtları al
    Then "name" sutunu boş olmamalıdır
    Then "id" sutunu boş olmamalıdır