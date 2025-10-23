@US_09
Feature: US_09 User tum siparisleri goruntuleyebilmeli

  Background:
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici "User" olarak giriş yapar
    And Kullanici 2 saniye bekler
    When Kullanici user panelden My Orders'a tiklar
    And Kullanici 2 saniye bekler
    And Sayfanin asagisina scroll yapılır


  @US_09_TC_01
  Scenario: TC_01-User tum siparislerini goruntuleyebilmeli

    And En az bir siparis karti gorunur
    And Her siparis kartinda zorunlu alanlar gecerli olur
    Then Sayfa kapatilir

  @US_09_TC_02
  Scenario: TC_02-User 4 statusun tamamini  durumundaki tum siparisleri gorunmeli

    And "CANCELED" durumlu en az bir siparis gorur
    And "PENDING" durumlu en az bir siparis gorur
    And "CONFIRMED" durumlu en az bir siparis gorur
    And "DELIVERED" durumlu en az bir siparis gorur
    Then Sayfa kapatilir

