@DB_US_12_Payment_Validation

Feature: Admin olarak contact message kategorisinde isim ve bilgilerin doğrulanması

  Background:
    Given Veritabanı bağlantısı kurulur

  Scenario: Contacat message isimleri ve bilgileri veritabanıyla uyuşmalı
    When Contact message tablosu veritabaninda aranir
    Then UI'deki tum contact message bilgileri veritabanindaki kayitlarla ayni olmali
    And Baglanti kapatilir


