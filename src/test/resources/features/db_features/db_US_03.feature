@DB_US_03_Cart_Item
Feature: cart_item Tablosu Sütun Adlari ve Verilerinin Dogrulanmasi

  @US_Carts_TC01
  Scenario:
        # Veritabanı bağlantısı kurulur ve sorgu çalıştırılır
    Given Veritabanı bağlantısı kurulur
        # Sütun adları doğrulanır
    When Kullanici "select * from snack_attack_db.cart_item" sorgusunu calistirir
        # Sütun adları doğrulanır
    Then Gelen cevapta asagidaki sutun isimleri dogrulanir:
      | line_total |
      | quantity   |
      | cart_id    |
      | created_at |
      | id         |
      | product_id |
      | updated_at |
        # Tablo verileri doğrulanır
    And Gelen cevapta asagidaki verilerin bulundugu dogrulanir:
      | line_total | quantity | cart_id | id | product_id |
      | 60         | 1        | 2       | 1  | 1          |
    And Baglanti kapatilir
