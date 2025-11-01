@DB_US_14_product_addition_category
Feature: Product_addition_category table doğrulamasi
  @US14_TC01
  Scenario: product_addition_category isimlerini ve bilgilerini doğrulama
    Given Veritabanı bağlantısı kurulur
    When "select product_id from snack_attack_db.product_addition_category where addition_category_id=3" sorgusu yapilir
    Then product_addition_category isimleri ve bilgileri doğrulanır
    And Baglanti kapatilir

  @US14_TC02
  Scenario: product_addition_category bilgileri ile product_additions tablosundaki isimleri doğrulama
    Given Veritabanı bağlantısı kurulur
    When "select name from snack_attack_db.product_additions where addition_category_id=3" sorgusu yapilir
    Then product_additions tablosundaki isimler doğrulanır
    And Baglanti kapatilir

  @US14_TC03
  Scenario: product_addition_category isimlerini ve bilgilerini doğrulama
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.product_additions where addition_category_id=3" sorgusu yapilir
    Then product_additions tablosundaki bütün datalar doğrulanır
    And Baglanti kapatilir

