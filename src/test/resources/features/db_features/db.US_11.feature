@DB_US_11_Order_Isimleri
Feature: Sipariş isimleri ve bilgileri doğrulanmalı
  Background:
    Given Veritabanıyla bağlantı kurulur

  Scenario: Sipariş isimleri ve bilgileri veritabanından doğrulanmalı
    When Siparis tablosu veritabaninda aranir
    Then Siparis Kategori bilgileri veritabaninda bulunmalidir
    And Baglanti kapatilir
