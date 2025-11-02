@E2E_Payment_Chain
@adminToken
Feature: Yeni ödeme oluşturma, veritabanında doğrulama ve admin panelinde görüntüleme



  Scenario: Yeni ödeme oluşturma ve doğrulama (API → DB → UI)

       # ---------- UI Aşaması ----------
    Given
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Payment Management'a tiklar
    Then Payments tablosundaki son ödeme bilgileri UI dan alinir



    # ---------- DB Aşaması ----------
    Given Veritabanı bağlantısı kurulur
    When Payments tablosundaki son ödeme bilgileri veritabaninda aranir
    Then DB’deki ödeme bilgileri UI’den dönen bilgilerle tutarlı olmalı





    # ---------- API Aşaması ----------
    Given "PaymentsLastPaymentID" endpoint'ine baglanti kurulur
    When Admin son ödeme bilgilerini almak icin GET istegi gonderir
    Then Status code 200 oldugu dogrulanir
    And DB’deki son ödeme bilgileri API’den dönen bilgilerle tutarlı olmalı
