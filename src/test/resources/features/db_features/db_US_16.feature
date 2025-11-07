@DB_US_16_product_category
Feature: product_category tablosundaki isimlerin ve bilgilerin dogrulanmasi

  @US16_TC01
  Scenario: product category tablosundaki isimleri ve bilgileri dogralama
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.product_category where product_id=110" sorgusu yapilir
    Then Urunun category idsi dogrulanir
    And Baglanti kapatilir


  @US16_TC02
  Scenario: product category tablosundaki isimlerin benzersizlik kontrolu
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.product_category where category_id=5" sorgusu yapilir
    Then Ayni categorye sahip urunlerin benzersiz product idye sahip oldugu dogrulanir
    And Baglanti kapatilir

  @US16_TC03
  Scenario: product tablosundaki bilgilerin kontrolu
    Given Veritabanı bağlantısı kurulur
    When "select * from snack_attack_db.product where id=38" sorgusu yapilir
    Then Urun bilgilerinin dogru oldugu kontrol edilir
    And Baglanti kapatilir