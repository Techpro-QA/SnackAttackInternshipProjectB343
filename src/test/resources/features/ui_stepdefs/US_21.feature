@US_21
Feature: Admin Contact Mesajlari gorebilmeli (Support Requests)

  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Support Requests'a tiklar

  @US_21 @US_21_TC01
  Scenario: TC_01 - Support request listesi goruntulenmeli
    Then Request kayitlarinin liste halinde goruldugu dogrulanir
    And Sayfa kapatilir

  @US_21 @US_21_TC02
  Scenario: TC_02 - Support request detayini (mesaj icerigini) goruntuleme.
    When Support request listesinde herhangi bir kaydin goruntuleme butonu tiklanir
    Then Detay ekraninin acildigi dogrulanir
    And Sayfa kapatilir

  @US_21 @US_21_TC03
  Scenario: TC_03 - Support Request Filtreleme - All
    When Filtre bolumune tiklanir
    And All secenegi secilir
    And Search butonuna tiklanir
    Then Tum requestlerin goruntulendigi dogrulanir
    And Sayfa kapatilir

  @US_21 @US_21_TC04
  Scenario: TC_04 - Support Request Filtreleme - Email
    When Filtre bolumune tiklanir
    And Email seceneği secilir
    And Detayı istenen email adresi adres kutusuna yazilir
    And Search butonuna tiklanir
    Then Secilen email adresi dısında maillere ait bilgilerin listelendigi dogrulanir

  @US_21 @US_21_TC05
  Scenario: TC_05 - Support Request Filtreleme - Subject
    When Filtre bolumune tiklanir
    And Subject seceneği secilir
    And Subject alanına "Uyarı" filtre kriteri girilir
    And Search butonuna tiklanir
    Then Sadece ilgili subjecte sahip Support Request'lerin listelendigi dogrulanir

  @US_21 @US_21_TC06
  Scenario: TC_06 - Support Request Filtreleme - Date
    When Filtre bolumune tiklanir
    And Date seceneği secilir
    And Date alanına date filtre kriteri girilir
    And Search butonuna tiklanir
    Then Sadece ilgili "23-10-2025" değerine sahip Support Request'in listelendiği doğrulanır

  @US_21 @US_21_TC07
  Scenario: TC_07 - Support Request Filtreleme - Time
    When Filtre bolumune tiklanir
    And Time secenegi secilir
    And Time alanına gecerli filtre kriteri girilir
    And Search butonuna tiklanir
    Then Sadece girilen time değerine sahip Support Request kayıtlarının listelendiği doğrulanır



  @US_21 @US_21_TC08
  Scenario: TC_08 - Support request ler silinebilmeli
    And Herhangi bir mesajin yaninda bulunan Delete ikonuna tiklanir
    And Onay penceresinde Yes secenegine tiklanir
    Then Basarili silme bildirimi "Message deleted successfully" gorulur


