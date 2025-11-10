@Order_item_additions
Feature: Order_item_additions sorguları

  Scenario: Order_item_additions tablosundaki verilerin dogrulanması
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.order_item_additions where addition_id=1" sorgusu yapilir
    Then Order_item_additions tablosundaki veriler doğrulanır
    And Baglanti kapatilir

  Scenario: Order_item_additions tablosundaki verilerin dogrulanması
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.order_item_additions where addition_id=3" sorgusu yapilir
    Then Order_item_additions tablosundaki farkli id'ye sahip veriler doğrulanır
    And Baglanti kapatilir

  Scenario: Order_item_additions tablosundaki verilerin dogrulanması
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.order_item_additions where order_item_id=86" sorgusu yapilir
    Then Order_item_additions tablosundaki order_item_id verileri doğrulanır
    And Baglanti kapatilir