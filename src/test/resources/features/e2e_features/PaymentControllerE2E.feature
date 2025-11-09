@paymentE2E
Feature: Yeni ödeme olusturma, veritabanında dogrulama ve admin panelinde görüntüleme


        #  API ASAMASI
  @userOrderToken
  Scenario: Yeni ödeme olusturma (API)
    Given "paymentscreatepayment" endpoint'ine baglanti kurulur
    When Yeni ödeme olusturmak icin POST istegi gonderilir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde olusturulan ödeme bilgisi dogrulanmali


        #  UI ASAMASI
  Scenario: Admin panelinde ödeme görüntüleme (UI)
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Payment Management'a tiklar
    Then Payments tablosundaki son ödeme bilgileri UI dan alinir
    And Sayfa kapatilir


        #  DB ASAMASI
  Scenario: Veritabanında ödeme kaydının dogrulanması (DB)
    Given Veritabanı bağlantısı kurulur
    When Payments tablosundaki son ödeme bilgileri veritabaninda aranir
    Then DB’deki ödeme bilgileri UI’den dönen bilgilerle tutarlı olmalı