@User_Controller
Feature: Kullanici işlemleri API doğrulamasi

  @patchUser
  Scenario: Kullanıcı geçerli token ile bilgilerini güncelleyebilmelidir
    Given "Login" endpoint'ine bağlantı kurulur ve geçerli token alınır
    When Kullanıcı "updateUser" endpoint'ine PATCH isteği gönderir
    Then Status code 200 olmalı
    And Response body içinde güncellenmiş kullanıcı bilgileri doğrulanmalı

  @getUser
  Scenario: Kullanıcı geçerli token ile kendi bilgilerini görüntüleyebilmelidir
    Given "Login" endpoint'ine bağlantı kurulur ve geçerli token alınır
    When Kullanıcı "getAuthenticatedUser" endpoint'ine GET isteği gönderir
    Then Status code 200 olmalı
    And Response body içinde kullanıcı email, isim ve adres bilgileri doğrulanmalı

  @getOrders
  Scenario: Kullanıcı geçerli token ile sipariş geçmişini görüntüleyebilmelidir
    Given "Login" endpoint'ine bağlantı kurulur ve geçerli token alınır
    When Kullanıcı "getOrdersByAuthUser" endpoint'ine GET isteği gönderir
    Then Status code 200 olmalı
    And Response body içinde sipariş listesi boş veya dolu olarak doğrulanmalı



