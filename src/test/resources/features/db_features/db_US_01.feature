
@DB_US_01_Validate_Tables

Feature: Database tablolarını doğrulama

  Scenario: Beklenen tabloların var olduğunu doğrula
    Given veritabanına bağlantı kurabilirim
    When beklenen tabloları sorgularım
    Then tüm tablolar mevcut olmalı
    And eksik tablo varsa hata mesajı dönmeli