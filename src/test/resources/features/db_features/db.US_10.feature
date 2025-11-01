@DB_US_10_Order_Items
Feature: Siparisteki urunlerin isimleri ve bilgileri doğrulanmali
  Background:
    Given Veritabanı bağlantısı kurulur


  Scenario: Siperisteki urunlerin isimleri ve  bilgileri veritabanından doğrulanmalı
    When Siparisteki urunlerin tablosu veritabaninda aranir
    Then Kategori bilgileri veritabaninda bulunmalidir
    And Baglanti kapatilir

