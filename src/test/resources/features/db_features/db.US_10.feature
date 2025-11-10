@DB_US_10_Order_Items
Feature: Siparişe ait ürün isimleri ve bilgileri doğrulanmalı
  Background:
    Given Veritabanıyla bağlantı kurulur

  Scenario: Siparişe ait ürün bilgileri veritabanından doğrulanmalı
    When Siparise ait urunlerin tablosu veritabaninda aranir
    Then Siparise ait urunlerin kategori bilgileri veritabaninda bulunmalidir
    And Baglanti kapatilir

