
@Sse_Service
Feature: Server-Sent Event Service Gercek Zamanli Veri Akisi

  Scenario: Kullanıcı geçerli token ile SSE servisine abone olabilir
    Given geçerli bearer token'a sahibim
    When /sse-service/subscribe endpoint'ine GET isteği gönderirim
    Then sunucu bağlantıyı açık tutmalı And herhangi bir 401 Unauthorized hatası dönmemelidir