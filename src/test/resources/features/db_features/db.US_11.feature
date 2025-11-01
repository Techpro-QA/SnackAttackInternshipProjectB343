@DB_US_11_Order_Isimleri
Feature: Siparis isimleri ve bilgileri doğrulanmali
  Background:
    Given Veritabanı bağlantısı kurulur


  Scenario: Siperis isimleri ve  bilgileri veritabanından doğrulanmalı
    When Siparis tablosu veritabaninda aranir
    Then Kategori bilgileri veritabaninda bulunmalidir
    And Baglanti kapatilir