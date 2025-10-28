@DB_US_15_Product_Validation
Feature: Admin OLarak Ürün Ekleme kategorilerinin isimlerinin ve bilgilerinin doğrulanması

  Background:
    Given Veritabanı bağlantısı kurulur


  Scenario: Ürün ekleme kategorilerinin bilgileri veritabanından doğrulanmalı
    When Urun ekleme kategorileri tablosu veritabaninda aranir
    Then Kategori bilgileri veritabaninda bulunmalidir
    And Baglanti kapatilir