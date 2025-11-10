@E2E_Admin_DB_API
Feature: Admin yönetimi, DB ve SSE servisi üzerinden tam E2E testi

  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Kullanici 2 saniye bekler
    And Admin Admin Management'a tiklar
    Then Tum admin listesinin goruntulendigini kontrol edilir

  Scenario: Admin silme islemi, DB ve SSE servisi ile birlikte kontrol edilir
    # UI adımları
    When Admin Admin Management listesinden herhangi bir kaydin cop kutusu icon'ina tiklar
    And Admin Allert mesajininda tamam'a tiklar
    Then Silinmesi hedeflenilen adminin listede olmadigi kontrol edilir

    # DB kontrolü
    Given veritabanına bağlantı kurabilirim
    When beklenen tabloları sorgularım
    Then tüm tablolar mevcut olmalı
    And eksik tablo varsa hata mesajı dönmeli
    And veritabanı bağlantısı kapatılır

    # SSE API kontrolü
    Given geçerli bearer token'a sahibim
    When /sse-service/subscribe endpoint'ine GET isteği gönderirim
    Then sunucu bağlantıyı açık tutmalı And herhangi bir 401 Unauthorized hatası dönmemelidir

    # Son adım
    And Sayfa kapatilir
