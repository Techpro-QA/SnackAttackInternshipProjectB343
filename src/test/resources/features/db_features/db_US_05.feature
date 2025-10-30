@DB_US_05_Carts
Feature: Carts tablosundaki veriler dogrulanabilmeli

  Background:
    Given Veritabanı bağlantısı kurulur

  @US_Carts_TC01
  Scenario: UI'daki cart bilgileri veritabanındaki kayıtlarla aynı olmalıdır
    Then UI'daki ilk cart bilgileri veritabanındaki kayıtla aynı olmalıdır