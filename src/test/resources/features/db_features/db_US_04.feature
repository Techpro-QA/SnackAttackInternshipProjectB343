@DB_US_04
Feature: Cart_item_additions isimleri ve bilgileri doğrulama

  Background:
    Given Veritabanı bağlantısı kurulur

  @DB_US_04_TC01
  Scenario: Cart_item_additions tablo verileri eslesmesi cart_item_id den addition_id
    Given "select cart_item_id from snack_attack_db.cart_item_additions where addition_id = 3" sorgusu yapilir
    Then Cart_item_additions tablo veri eslesmeleri dogrulanir "2,5,8,168,170,184"
    And Baglanti kapatilir

  @DB_US_04_TC02
  Scenario: Cart_item_additions tablo verileri eslesmesi addition_id den cart_item_id
    Given "select addition_id from snack_attack_db.cart_item_additions where cart_item_id = 60" sorgusu yapilir
    Then Cart_item_additions tablo veri eslesmeleri dogrulanir "7,8,9"
    And Baglanti kapatilir

