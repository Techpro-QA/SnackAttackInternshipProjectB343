@US_12
Feature: Admin tüm siparişleri görüntüleyebilmeli ve listeleyebilmeli (order management)

  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Kullanici 2 saniye bekler
    And Admin Order Management'a tiklar

  @US12_TC01
  Scenario: TC_01 - Admin tüm siparişleri listeleyebilmeli ve görüntüleyebilmelidir
    When Admin "Order Management" görüntüler
    And Admin "Total Price" başlığını görüntüler
    And Admin "Date" başlığını görüntüler
    And Admin "Status" başlığını görüntüler
    And Admin "Payment" başlığını görüntüler
    And Sayfa kapatilir

  @US12_TC02
  Scenario: TC_02 - Admin sipariş detaylarını görüntüleyebilmelidir
    And Admin Order Details sayfasını görüntüler
    And Sayfa kapatilir

  @US12_TC03
  Scenario Outline: TC_03 - Admin PENDING olan sipariş durumunu değiştirebilmelidir
    When Admin "<mevcutDurum>" durumundaki siparişi "<yeniDurum>" olarak değiştirir
    And Sayfa kapatilir

    Examples:
      | mevcutDurum | yeniDurum |
      | PENDING     | DELIVERED |
      | PENDING     | CONFIRMED |