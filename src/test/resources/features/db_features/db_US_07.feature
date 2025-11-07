@DB_US_O7
Feature: Contact_message isimlerinin ve bilgilerinin dogrulanması

  Background:
    Given Veritabanı bağlantısı kurulur

  Scenario Outline: Contact_message tablosunda belirli email adresleri ve bilgileri dogrulanmali
    When "<email>" email adresine ait kayit sorgulanır
    Then Email "<email>" icin name "<name>" message "<message>" subject "<subject>" olmalidir
    And Baglanti kapatilir
    Examples:
      | email                    | name    | message                 | subject   |
      | amancigdem@gmail.com     | cigdem  | abc                     | Malıyet   |
      | hayriye25@gmail.com      | Hayriye | Ne yazacagımı bilemedim | Ombudsman |
      | abrahan.rylin@fsitip.com | dfeef   | Ne ararsın              | Marka     |
