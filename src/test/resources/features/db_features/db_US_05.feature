@DB_US_05_Carts
Feature: Carts tablosundaki veriler dogrulanabilmeli

  Background:
    Given Veritabanı bağlantısı kurulur

  @US_Carts_TC01
  Scenario: UI'daki cart bilgileri veritabanındaki kayıtlarla aynı olmalıdır

    When Carts tablosundaki veriler sorgulanir
    Then User id'leri "6" ve "10" olan cart bilgileri dogrulanir
